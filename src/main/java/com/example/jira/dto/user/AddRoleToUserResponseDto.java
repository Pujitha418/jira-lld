package com.example.jira.dto.user;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(setterPrefix = "set")
public class AddRoleToUserResponseDto {
    private boolean isAdded = false;
    private String error;
}
