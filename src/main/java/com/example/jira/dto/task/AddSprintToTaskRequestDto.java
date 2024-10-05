package com.example.jira.dto.task;

import lombok.Data;

@Data
public class AddSprintToTaskRequestDto {
    private String sprintName;
    private String taskId;
}
