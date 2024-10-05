package com.example.jira.models.task;

import com.example.jira.enums.LinkType;
import com.example.jira.models.BaseModel;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TaskLinks extends BaseModel {
    //link type - enum
    @ManyToOne
    private Task sourceTask;
    @ManyToOne
    private Task destinationTask;
    private LinkType linkType;
}
