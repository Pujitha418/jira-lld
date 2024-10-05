package com.example.jira.models.user;

import com.example.jira.enums.UserRole;
import com.example.jira.models.BaseModel;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder(setterPrefix = "set")
@AllArgsConstructor
@NoArgsConstructor
public class Role extends BaseModel {
    private String role;
}
