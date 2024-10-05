package com.example.jira.models.attachment;

import com.example.jira.models.BaseModel;
import com.example.jira.models.TaskComment;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class CommentAttachment extends BaseModel {
    @ManyToOne
    private TaskComment taskComment;
    @OneToOne
    private Attachment attachment;
}
