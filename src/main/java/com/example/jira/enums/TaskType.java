package com.example.jira.enums;

import lombok.Getter;

import java.util.List;

@Getter
public enum TaskType {
    EPIC("Epic", true),
    SUBTASK("SubTask", null, false),
    TASK("Task", List.of(SUBTASK), false),
    STORY("Story", List.of(TASK, SUBTASK), false)
    ;


    private final String type;
    private List<TaskType> allowedChildTaskTypes;
    private final boolean isRoot;

    TaskType(String type, boolean isRoot) {
        this.type = type;
        this.isRoot = isRoot;
    }

    TaskType(String type, List<TaskType> allowedChildTaskTypes, boolean isRoot) {
        this.type = type;
        this.allowedChildTaskTypes = allowedChildTaskTypes;
        this.isRoot = isRoot;
    }
}
