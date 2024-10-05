package com.example.jira.models.attachment;

import com.example.jira.models.BaseModel;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Attachment extends BaseModel {
    private String fileName;
    private String fileUrl;
}
