package com.example.jira.controller;

import com.example.jira.dto.role.AddRoleRequestDto;
import com.example.jira.dto.role.AddRoleResponseDto;
import com.example.jira.dto.user.CreateUserResponseDto;
import com.example.jira.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/role")
public class RoleController {
    private RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping(path = "/create", produces = "application/json")
    public ResponseEntity<AddRoleResponseDto> createRole(@RequestBody AddRoleRequestDto addRoleRequestDto) {
        return ResponseEntity.ok().body(roleService.createRole(addRoleRequestDto));
    }
}
