package com.example.jira.services.workflow;

import com.example.jira.constants.ProjectConstants;
import com.example.jira.dto.AddWorkflowStepRequestDto;
import com.example.jira.dto.CreateProjectWorkflowRequestDto;
import com.example.jira.exceptions.ProjectNotFoundException;
import com.example.jira.models.Project;
import com.example.jira.models.workflow.ProjectWorkflowSequence;
import com.example.jira.repository.workflow.ProjectWorkflowRepository;
import com.example.jira.services.ProjectService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectWorkflowService {
    private ProjectWorkflowRepository projectWorkflowRepository;
    private ProjectService projectService;

    public ProjectWorkflowService(ProjectWorkflowRepository projectWorkflowRepository, ProjectService projectService) {
        this.projectWorkflowRepository = projectWorkflowRepository;
        this.projectService = projectService;
    }

    public Optional<ProjectWorkflowSequence> getProjectWorkflowInitialStatus(Long projectId) {
        return  projectWorkflowRepository.getInitialStatus(projectId);
    }

    public Optional<ProjectWorkflowSequence> getValidNextWorkflowStatus(Long projectId, String currentStatus, String previousStatus) {
        return projectWorkflowRepository.getProjectWorkflow(projectId, currentStatus, previousStatus);
    }

    public String initializeWorkflowForProject(CreateProjectWorkflowRequestDto projectWorkflowRequestDto) throws ProjectNotFoundException {
        ProjectWorkflowSequence projectWorkflowSequence = new ProjectWorkflowSequence();
        Optional<Project> project = projectService.getProject(projectWorkflowRequestDto.getProjectId());
        if (project.isEmpty()) {
            throw new ProjectNotFoundException();
        }
        projectWorkflowSequence.setProject(project.get());
        projectWorkflowSequence.setNextSequence(ProjectConstants.DEFAULT_STATUS);
        projectWorkflowSequence.setIsActive(1);
        projectWorkflowRepository.save(projectWorkflowSequence);

        return "SUCCESS";
    }

    public String addWorkflowStepForProject(AddWorkflowStepRequestDto addWorkflowStepRequestDto) throws ProjectNotFoundException {
        Optional<Project> project = projectService.getProject(addWorkflowStepRequestDto.getProjectId());
        if (project.isEmpty()) {
            throw new ProjectNotFoundException();
        }
        ProjectWorkflowSequence projectWorkflowSequence = new ProjectWorkflowSequence();
        projectWorkflowSequence.setIsActive(1);
        projectWorkflowSequence.setProject(project.get());
        projectWorkflowSequence.setPreviousSequence(addWorkflowStepRequestDto.getOldState());
        projectWorkflowSequence.setNextSequence(addWorkflowStepRequestDto.getNewState());
        //projectWorkflowRepository.insertStep(addWorkflowStepRequestDto.getProjectId(), addWorkflowStepRequestDto.getNewState(), addWorkflowStepRequestDto.getOldState());
        projectWorkflowRepository.save(projectWorkflowSequence);
        return "SUCCESS";
    }
}
