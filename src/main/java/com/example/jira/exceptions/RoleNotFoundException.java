package com.example.jira.exceptions;

public class RoleNotFoundException extends Exception {
    public RoleNotFoundException(String roleName) {
        super(String.format("User not found for email %s", roleName));
    }
}
