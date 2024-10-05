package com.example.jira.exceptions;

public class ProjectNotFoundException extends Exception {
    public ProjectNotFoundException() {
        super("Invalid Project");
    }
}
