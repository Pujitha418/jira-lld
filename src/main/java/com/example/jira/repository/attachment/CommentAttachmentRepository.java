package com.example.jira.repository.attachment;

import com.example.jira.models.attachment.CommentAttachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentAttachmentRepository extends JpaRepository<CommentAttachment, Long> {
}
