package com.example.jira.models;

import com.example.jira.models.workflow.ProjectWorkflowSequence;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Project extends BaseModel {
    private String name;
    private String taskIdPrefix;
    @OneToMany(mappedBy = "project")
    private List<ProjectWorkflowSequence> workflow;
}
