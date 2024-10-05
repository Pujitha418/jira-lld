package com.example.jira.repository.workflow;

import com.example.jira.models.workflow.ProjectWorkflowSequence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ProjectWorkflowRepository extends JpaRepository<ProjectWorkflowSequence, Long> {
    @Query(value = "select * from project_workflow_sequence where project_id = :projectId and previous_sequence is null limit 1", nativeQuery = true)
    Optional<ProjectWorkflowSequence> getInitialStatus(@Param("projectId") Long projectId);

    @Query(value = "select * from project_workflow_sequence where project_id = :projectId and next_sequence = :newState and previous_sequence = :currState", nativeQuery = true)
    Optional<ProjectWorkflowSequence> getProjectWorkflow(@Param("projectId") Long projectId, @Param("newState") String newState, @Param("currState") String currState);

    @Query(value = "select * from project_workflow_sequence where project_id = :projectId and sequence = :sequence", nativeQuery = true)
    Optional<ProjectWorkflowSequence> getNextStatuses(@Param("projectId") Long projectId, @Param("sequence") int sequence);

    @Modifying
    @Query(value = "insert into project_workflow_sequence(project_id,next_sequence,current_sequence,is_active)  values (:projectId, :newState, :currState, 1)", nativeQuery = true)
    void insertStep(@Param("projectId") Long projectId, @Param("newState") String newState, @Param("currState") String currState);
}
