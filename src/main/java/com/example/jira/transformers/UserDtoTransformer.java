package com.example.jira.transformers;

import com.example.jira.dto.user.AddRoleToUserResponseDto;
import com.example.jira.dto.user.CreateUserResponseDto;
import com.example.jira.models.user.User;

public class UserDtoTransformer {
    public static CreateUserResponseDto transformUserEntityToCreateUserResponseDto(User user) {
        boolean isCreated = user != null;
        return CreateUserResponseDto.builder()
                .setIsCreated(isCreated)
                .build();
    }

    public static AddRoleToUserResponseDto transformUserEntityToAddRoleToUserResponseDto(User user) {
        boolean isAdded = user != null;
        return AddRoleToUserResponseDto.builder()
                .setIsAdded(isAdded)
                .build();
    }
}
