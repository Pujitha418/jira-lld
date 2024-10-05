package com.example.jira.controller;

import com.example.jira.dto.CreateProjectRequestDto;
import com.example.jira.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/project")
public class ProjectController {
    private ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping(value = "/create", produces = "application/json")
    public ResponseEntity<Boolean> createProject(@RequestBody CreateProjectRequestDto createProjectRequestDto) {
        return ResponseEntity.accepted().body(projectService.createProject(createProjectRequestDto));

    }
}
