package com.example.jira.dto.user;

import com.example.jira.enums.UserRole;
import lombok.Data;

@Data
public class AddRoleToUserRequestDto {
    private String userEmail;
    private String role;
}
