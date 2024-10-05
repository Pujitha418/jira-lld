package com.example.jira.exceptions;

public class ProjectWorkflowMissingException extends Exception {
    public ProjectWorkflowMissingException() {
        super("Workflow not must be setup prior to creating tasks");
    }
}
