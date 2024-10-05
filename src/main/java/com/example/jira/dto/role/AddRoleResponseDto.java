package com.example.jira.dto.role;

import com.example.jira.enums.ActionType;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder(setterPrefix = "set")
public class AddRoleResponseDto {
    private String roleName;
    private List<ActionType> actionTypes;
}

