package com.example.jira.controller;

import com.example.jira.dto.AddWorkflowStepRequestDto;
import com.example.jira.dto.CreateProjectWorkflowRequestDto;
import com.example.jira.exceptions.ProjectNotFoundException;
import com.example.jira.services.workflow.ProjectWorkflowService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/project/workflow")
public class ProjectWorkflowController {
    private ProjectWorkflowService projectWorkflowService;

    public ProjectWorkflowController(ProjectWorkflowService projectWorkflowService) {
        this.projectWorkflowService = projectWorkflowService;
    }

    @PostMapping(value = "/create", produces = "application/json")
    public ResponseEntity<String> createWorkflow(@RequestBody CreateProjectWorkflowRequestDto createProjectWorkflowRequestDto) {
        try {
            return ResponseEntity.accepted().body(projectWorkflowService.initializeWorkflowForProject(createProjectWorkflowRequestDto));
        } catch (ProjectNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping(value = "/addStep", produces = "application/json")
    public ResponseEntity<String> addWorkflowSteps(@RequestBody AddWorkflowStepRequestDto addWorkflowStepRequestDto) {
        try {
            return ResponseEntity.accepted().body(projectWorkflowService.addWorkflowStepForProject(addWorkflowStepRequestDto));
        } catch (ProjectNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
