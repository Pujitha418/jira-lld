package com.example.jira.dto.task;

import com.example.jira.enums.TaskType;
import com.example.jira.models.Project;
import com.example.jira.models.Sprint;
import com.example.jira.models.user.User;
import lombok.Data;

import java.util.List;

@Data
public class CreateTaskRequestDto {
    private String parentId;
    private String description;
    private TaskType taskType;
    private Long reportedByUserId;
    private List<Sprint> sprints;
    private Long projectId;
}
