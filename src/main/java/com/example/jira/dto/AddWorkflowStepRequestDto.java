package com.example.jira.dto;

import lombok.Data;

@Data
public class AddWorkflowStepRequestDto {
    private Long projectId;
    private String newState;
    private String oldState;
}
