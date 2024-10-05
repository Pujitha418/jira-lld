package com.example.jira.controller;

import com.example.jira.dto.user.AddRoleToUserRequestDto;
import com.example.jira.dto.user.AddRoleToUserResponseDto;
import com.example.jira.dto.user.CreateUserRequestDto;
import com.example.jira.dto.user.CreateUserResponseDto;
import com.example.jira.exceptions.RoleNotFoundException;
import com.example.jira.exceptions.UserNotFoundException;
import com.example.jira.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(path = "/create", produces = "application/json")
    public ResponseEntity<CreateUserResponseDto> createUser(@RequestBody CreateUserRequestDto createUserRequestDto) {
        return ResponseEntity.ok(userService.createUser(createUserRequestDto));
    }

    @PostMapping(path = "/addRole", produces = "application/json")
    public ResponseEntity<AddRoleToUserResponseDto> addRoleToUser(@RequestBody AddRoleToUserRequestDto addRoleToUserRequestDto) {
        try {
            return ResponseEntity.ok(userService.assignRoleToUser(addRoleToUserRequestDto));
        } catch (UserNotFoundException | RoleNotFoundException e) {
            return ResponseEntity.badRequest().body(
                    AddRoleToUserResponseDto.builder()
                            .setError(e.getMessage())
                            .build());
        }
    }
}
