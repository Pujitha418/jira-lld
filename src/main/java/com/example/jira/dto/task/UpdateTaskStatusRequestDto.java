package com.example.jira.dto.task;

import lombok.Builder;
import lombok.Data;

@Data
public class UpdateTaskStatusRequestDto {
    private Long projectId;
    private String taskId;
    private Long userId;
    private String currentStatus;
    private String newStatus;
}
