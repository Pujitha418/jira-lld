package com.example.jira.models;

import com.example.jira.models.task.Task;
import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TaskComment extends BaseModel {
    @ManyToOne
    private Task task;
    @Lob
    private String comment;
}
