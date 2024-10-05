package com.example.jira.models.task;

import com.example.jira.enums.TaskType;
import com.example.jira.models.BaseModel;
import com.example.jira.models.Project;
import com.example.jira.models.Sprint;
import com.example.jira.models.TaskComment;
import com.example.jira.models.attachment.TaskAttachment;
import com.example.jira.models.user.User;
import com.example.jira.models.workflow.ProjectWorkflowSequence;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Task extends BaseModel {
    private String taskId;
    private String description;
    //private Task parent;
    //private Task child;
    private TaskType taskType;
    @ManyToOne
    private User reported;
    @ManyToMany
    private List<Sprint> sprints;
    @ManyToOne
    private Project project;
    @ManyToOne
    private ProjectWorkflowSequence projectWorkflowSequence;
    @OneToMany
    private List<TaskAttachment> taskAttachments;
    @OneToMany
    private List<TaskComment> taskComments;
}
