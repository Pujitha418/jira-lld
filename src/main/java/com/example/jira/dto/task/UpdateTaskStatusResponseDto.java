package com.example.jira.dto.task;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(setterPrefix = "set")
public class UpdateTaskStatusResponseDto {
    private Boolean isUpdated;
}
