package com.example.jira.dto.task;

import com.example.jira.enums.LinkType;
import lombok.Data;

@Data
public class AddTaskLinkRequestDto {
    private String sourceTaskId;
    private String destinationTaskId;
    private LinkType relation;
}
