package com.example.jira.dto.task;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(setterPrefix = "set")
public class TaskState {
    private Long projectId;
    private String taskId;
    private String currentStatus;
    private String nextStatus;
}
