package com.example.jira.controller;

import com.example.jira.dto.task.CreateTaskRequestDto;
import com.example.jira.dto.task.CreateTaskResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sprint")
public class SprintController {

    @PostMapping(path="/create", produces = "application/json")
    public void createSprintTask() {

    }
}
