package com.example.jira.transformers;

import com.example.jira.dto.task.CreateTaskResponseDto;
import com.example.jira.dto.task.UpdateTaskStatusResponseDto;
import com.example.jira.models.task.Task;

public class TaskDtoTransformer {
    public static CreateTaskResponseDto transformTaskToCreateTaskResponseDto(Task task) {
        return CreateTaskResponseDto.builder()
                .setTaskId(task.getTaskId())
                .build();
    }

    public static UpdateTaskStatusResponseDto transformTaskToUpdateTaskStatusResponseDto(Task task) {
        boolean isUpdated = task!=null;
        return UpdateTaskStatusResponseDto.builder()
                .setIsUpdated(isUpdated)
                .build();
    }
}
