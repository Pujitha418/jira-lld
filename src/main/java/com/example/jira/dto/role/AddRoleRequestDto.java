package com.example.jira.dto.role;

import com.example.jira.enums.ActionType;
import com.example.jira.enums.UserRole;
import lombok.Data;

import java.util.List;

@Data
public class AddRoleRequestDto {
    private String roleName;
    private List<ActionType> actionTypes;
}
