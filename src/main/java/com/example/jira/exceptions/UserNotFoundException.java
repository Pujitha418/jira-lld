package com.example.jira.exceptions;

public class UserNotFoundException extends Exception {
    public UserNotFoundException(String email) {
        super(String.format("User not found for email %s", email));
    }

    public UserNotFoundException() {
        super("User not found for email");
    }
}
