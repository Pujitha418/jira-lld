package com.example.jira.transformers;

import com.example.jira.dto.role.AddRoleResponseDto;
import com.example.jira.models.user.Role;

public class RoleDtoTransformer {
    public static AddRoleResponseDto transformRoleEntityToAddRoleResponseDto(Role role) {
        boolean isCreated = role!=null;
        assert role != null;
        return AddRoleResponseDto
                .builder()
                .setRoleName(role.getRole().toString())
                .build();
    }
}
