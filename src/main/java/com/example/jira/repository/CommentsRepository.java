package com.example.jira.repository;

import com.example.jira.models.TaskComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentsRepository extends JpaRepository<TaskComment, Long> {
}
