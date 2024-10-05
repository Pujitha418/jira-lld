package com.example.jira.repository.sprint;

import com.example.jira.models.Sprint;
import com.example.jira.models.task.SprintTasks;
import com.example.jira.models.task.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SprintTasksRepository extends JpaRepository<SprintTasks, Long> {

    @Query(value = "select sprint_id from sprint_tasks where task_id = :taskId", nativeQuery = true)
    public Optional<List<Sprint>> getSprintForTasks(@Param("taskId") Long taskId);

    @Query(value = "select task_id from sprint_tasks where sprint_id = :sprintId", nativeQuery = true)
    public Optional<List<Task>> getTasksForSprint(@Param("sprintId") Long sprintId);
}
