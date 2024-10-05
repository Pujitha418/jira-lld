package com.example.jira.services;

import com.example.jira.enums.ActionType;
import com.example.jira.models.user.Role;

public interface RoleAccessServiceProvider {
    boolean isAllowed(ActionType actionType, Role role);
    boolean addActionAccessToRole(ActionType actionType, Role role);
    boolean removeActionAccessToRole(ActionType actionType, Role role);
}
