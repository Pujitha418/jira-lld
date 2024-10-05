package com.example.jira.services.task;

import com.example.jira.dto.task.*;
import com.example.jira.enums.LinkType;
import com.example.jira.exceptions.ProjectNotFoundException;
import com.example.jira.exceptions.ProjectWorkflowMissingException;
import com.example.jira.exceptions.TaskNotFoundException;
import com.example.jira.exceptions.UserNotFoundException;
import com.example.jira.models.Project;
import com.example.jira.models.Sprint;
import com.example.jira.models.task.SprintTasks;
import com.example.jira.models.task.Task;
import com.example.jira.models.user.User;
import com.example.jira.models.workflow.ProjectWorkflowSequence;
import com.example.jira.repository.ProjectRepository;
import com.example.jira.repository.sprint.SprintTasksRepository;
import com.example.jira.repository.task.TaskLinksRepository;
import com.example.jira.repository.task.TaskRepository;
import com.example.jira.repository.user.UserRepository;
import com.example.jira.repository.workflow.ProjectWorkflowRepository;
import com.example.jira.services.ProjectService;
import com.example.jira.services.sprint.SprintService;
import com.example.jira.services.user.UserService;
import com.example.jira.services.workflow.ProjectWorkflowService;
import com.example.jira.transformers.TaskDtoTransformer;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    private TaskRepository taskRepository;
    private SprintTasksRepository sprintTasksRepository;
    private UserService userService;
    private SprintService sprintService;
    private ProjectService projectService;
    private TaskLinksService taskLinksService;
    private ProjectWorkflowService projectWorkflowService;

    @Autowired
    public TaskService(TaskRepository taskRepository, TaskLinksRepository taskLinksRepository,
                       ProjectRepository projectRepository, UserRepository userRepository,
                       ProjectWorkflowRepository projectWorkflowRepository, SprintTasksRepository sprintTasksRepository, UserService userService, SprintService sprintService, ProjectService projectService, TaskLinksService taskLinksService, ProjectWorkflowService projectWorkflowService) {
        this.taskRepository = taskRepository;
        this.sprintTasksRepository = sprintTasksRepository;
        this.userService = userService;
        this.sprintService = sprintService;
        this.projectService = projectService;
        this.taskLinksService = taskLinksService;
        this.projectWorkflowService = projectWorkflowService;
    }


    public CreateTaskResponseDto createTask(CreateTaskRequestDto createTaskRequestDto)
            throws ProjectNotFoundException, ProjectWorkflowMissingException, UserNotFoundException {
        //1. check if user has access
        //2. check if a child can be crated for this parent
        //3. check if project exists
        Optional<User> user = userService.getUserRecord(createTaskRequestDto.getReportedByUserId());
        Optional<Project> project = projectService.getProject(createTaskRequestDto.getProjectId());
        if (project.isEmpty()) {
            throw new ProjectNotFoundException();
        }
        if (user.isEmpty()) {
            throw new UserNotFoundException();
        }
        //4. get initial status of project
        Optional<ProjectWorkflowSequence> projectWorkflowSequence =
                projectWorkflowService.getProjectWorkflowInitialStatus(createTaskRequestDto.getProjectId());
        if (projectWorkflowSequence.isEmpty()) {
            throw new ProjectWorkflowMissingException();
        }
        //5. create task
        Task task = new Task();
        task.setDescription(createTaskRequestDto.getDescription());
        task.setTaskType(createTaskRequestDto.getTaskType());
        task.setProject(project.get());
        if (createTaskRequestDto.getSprints() != null) {
            task.setSprints(Collections.singletonList(createTaskRequestDto.getSprints().get(0)));
        }

        task.setReported(user.get());
        task.setProjectWorkflowSequence(projectWorkflowSequence.get());
        Task savedTask = taskRepository.save(task);

        savedTask.setTaskId(project.get().getTaskIdPrefix() + savedTask.getId().toString());
        taskRepository.save(savedTask);

        //6. if parent is not null, add link
        if (createTaskRequestDto.getParentId() != null) {
            Optional<Task> parentTask = taskRepository.findByTaskId(createTaskRequestDto.getParentId());
            parentTask.ifPresent(value -> taskLinksService.addTaskLinks(value, savedTask, LinkType.CHILD));
        }

        return TaskDtoTransformer.transformTaskToCreateTaskResponseDto(savedTask);
    }

    public UpdateTaskStatusResponseDto progressTask(TaskState currentState, Long userId)
            throws ProjectNotFoundException, TaskNotFoundException {
        Optional<Project> project = projectService.getProject(currentState.getProjectId());
        if (project.isEmpty()) {
            throw new ProjectNotFoundException();
        }
        Optional<User> user = userService.getUserRecord(userId);
        Optional<Task> task = taskRepository.findByTaskId(currentState.getTaskId());
        if (task.isEmpty()) {
            throw new TaskNotFoundException(currentState.getTaskId());
        }

        Task updatedTask = null;
        Optional<ProjectWorkflowSequence> validPossibleState = projectWorkflowService
                .getValidNextWorkflowStatus(currentState.getProjectId(), currentState.getNextStatus(), currentState.getCurrentStatus());
        if (validPossibleState.isPresent()) {
            updatedTask = task.get();
            updatedTask.setProjectWorkflowSequence(validPossibleState.get());
            //TODO: Add task history or last updated by
            //updatedTask.set
            taskRepository.save(updatedTask);
        }
        return TaskDtoTransformer.transformTaskToUpdateTaskStatusResponseDto(updatedTask);
    }

    public void addTaskLinks(String sourceTaskId, String destinationTaskId, LinkType relation) throws TaskNotFoundException {
        Optional<Task> sourceNode = taskRepository.findByTaskId(sourceTaskId);
        Optional<Task> destinationNode = taskRepository.findByTaskId(destinationTaskId);
        if (sourceNode.isPresent() && destinationNode.isPresent()) {
            taskLinksService.addTaskLinks(sourceNode.get(), destinationNode.get(), relation);
        } else {
            if (sourceNode.isEmpty()) {
                throw new TaskNotFoundException(sourceTaskId);
            }
            throw new TaskNotFoundException(destinationTaskId);
        }
    }

    public Boolean addSprintToTask(AddSprintToTaskRequestDto addSprintToTaskRequestDto) {
        Optional<Task> task = taskRepository.findByTaskId(addSprintToTaskRequestDto.getTaskId());
        Optional<Sprint> sprint = sprintService.getSprint(addSprintToTaskRequestDto.getSprintName());
        if (task.isPresent() && sprint.isPresent()) {
            saveSprintTaskChanges(task.get(), sprint.get());
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    @Transactional
    private void saveSprintTaskChanges(Task task, Sprint sprint) {
        sprintTasksRepository.save(new SprintTasks(sprint, task));
        task.getSprints().add(sprint);
        taskRepository.save(task);
    }

    private boolean isNextStateInvalid(List<ProjectWorkflowSequence> projectWorkflowSequences, String nextStatus) {
        for (ProjectWorkflowSequence projectWorkflowSequence: projectWorkflowSequences) {
            if (projectWorkflowSequence.getPreviousSequence().equals(nextStatus)) {
                return true;
            }
        }
        return false;
    }

    private ProjectWorkflowSequence getNextStateWithName(List<ProjectWorkflowSequence> projectWorkflowSequences, String nextStatus) {
        for (ProjectWorkflowSequence projectWorkflowSequence: projectWorkflowSequences) {
            if (projectWorkflowSequence.getPreviousSequence().equals(nextStatus)) {
                return projectWorkflowSequence;
            }
        }
        return null;
    }

}
