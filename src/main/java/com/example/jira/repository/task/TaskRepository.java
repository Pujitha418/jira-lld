package com.example.jira.repository.task;

import com.example.jira.models.task.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TaskRepository extends CrudRepository<Task, Long> {
    @Query(value = "select * from task where task_id = :taskId", nativeQuery = true)
    Optional<Task> findByTaskId(@Param("taskId") String taskId);
}
