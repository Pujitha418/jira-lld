package com.example.jira.dto.user;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(setterPrefix = "set")
public class CreateUserResponseDto {
    private boolean isCreated;
}
