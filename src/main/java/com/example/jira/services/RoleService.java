package com.example.jira.services;

import com.example.jira.dto.role.AddRoleRequestDto;
import com.example.jira.dto.role.AddRoleResponseDto;
import com.example.jira.enums.UserRole;
import com.example.jira.models.user.Role;
import com.example.jira.repository.user.RoleRepository;
import com.example.jira.transformers.RoleDtoTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    private RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public AddRoleResponseDto createRole(AddRoleRequestDto addRoleRequestDto) {
        Role role = Role.builder()
                .setRole(addRoleRequestDto.getRoleName())
                .build();
        Role savedRole = roleRepository.save(role);
        return RoleDtoTransformer.transformRoleEntityToAddRoleResponseDto(savedRole);
    }
}
