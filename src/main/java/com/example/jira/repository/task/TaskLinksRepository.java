package com.example.jira.repository.task;

import com.example.jira.models.task.TaskLinks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskLinksRepository extends JpaRepository<TaskLinks, Long> {

}
