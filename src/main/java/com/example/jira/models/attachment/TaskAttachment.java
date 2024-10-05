package com.example.jira.models.attachment;

import com.example.jira.models.BaseModel;
import com.example.jira.models.task.Task;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class TaskAttachment extends BaseModel {
    @ManyToOne
    private Task task;
    @OneToOne
    private Attachment attachment;
}
