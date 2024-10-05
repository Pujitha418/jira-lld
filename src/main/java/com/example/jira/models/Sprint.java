package com.example.jira.models;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Sprint extends BaseModel {
    private String name;
    private String startDateEpoch;
    private String endDateEpoch;
    private Boolean isActive;
}
