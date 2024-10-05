package com.example.jira.exceptions;

public class TaskNotFoundException extends Exception {
    public TaskNotFoundException(String taskId) {
        super(String.format("Task %s Not Found", taskId));
    }
}
