package com.example.jira.models.workflow;

import com.example.jira.models.BaseModel;
import com.example.jira.models.Project;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProjectWorkflowSequence extends BaseModel {
    @ManyToOne
    private Project project;
    private String previousSequence;
    private String nextSequence;
    private int isActive = 1;
}
//todo -> open -> close
//todo -> close
