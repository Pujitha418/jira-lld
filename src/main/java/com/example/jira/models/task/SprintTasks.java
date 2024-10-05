package com.example.jira.models.task;

import com.example.jira.models.BaseModel;
import com.example.jira.models.Sprint;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class SprintTasks extends BaseModel {
    @ManyToOne
    private Sprint sprint;
    @OneToOne
    private Task task;

}
