package com.example.jira.controller;

import com.example.jira.dto.task.*;
import com.example.jira.exceptions.ProjectNotFoundException;
import com.example.jira.exceptions.ProjectWorkflowMissingException;
import com.example.jira.exceptions.TaskNotFoundException;
import com.example.jira.exceptions.UserNotFoundException;
import com.example.jira.services.task.TaskLinksService;
import com.example.jira.services.task.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/task")
public class TaskController {
    private TaskService taskService;
    private TaskLinksService taskLinksService;

    public TaskController(TaskService taskService, TaskLinksService taskLinksService) {
        this.taskService = taskService;
        this.taskLinksService = taskLinksService;
    }

    @PostMapping(path="/create", produces = "application/json")
    public ResponseEntity<CreateTaskResponseDto> createTask(@RequestBody CreateTaskRequestDto createTaskRequestDto) {
        try {
            return ResponseEntity.accepted().body(taskService.createTask(createTaskRequestDto));
        } catch (ProjectNotFoundException | ProjectWorkflowMissingException | UserNotFoundException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping(path="/updateStatus", produces = "application/json")
    public ResponseEntity<UpdateTaskStatusResponseDto> updateTaskStatus(@RequestBody UpdateTaskStatusRequestDto updateTaskStatusRequestDto) {
        TaskState taskState = TaskState.builder()
                .setProjectId(updateTaskStatusRequestDto.getProjectId())
                //.setUserId(updateTaskStatusRequestDto.getUserId())
                .setTaskId(updateTaskStatusRequestDto.getTaskId())
                .setCurrentStatus(updateTaskStatusRequestDto.getCurrentStatus())
                .setNextStatus(updateTaskStatusRequestDto.getNewStatus())
                .build();
        try {
            return ResponseEntity.accepted().body(taskService.progressTask(taskState, updateTaskStatusRequestDto.getUserId()));
        } catch (ProjectNotFoundException | TaskNotFoundException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping(path="/addLink", produces = "application/json")
    public ResponseEntity<String> addLinks(@RequestBody AddTaskLinkRequestDto addTaskLinkRequestDto) {
        try {
            taskService.addTaskLinks(addTaskLinkRequestDto.getSourceTaskId(), addTaskLinkRequestDto.getDestinationTaskId(), addTaskLinkRequestDto.getRelation());
            return ResponseEntity.accepted().body("SUCCESS");
        } catch (TaskNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping(path="/addSprint", produces = "application/json")
    public ResponseEntity<Boolean> addSprint(@RequestBody AddSprintToTaskRequestDto addSprintToTaskRequestDto) {
        return ResponseEntity.ok(taskService.addSprintToTask(addSprintToTaskRequestDto));
    }

    //TODO: Add clone task api

}
