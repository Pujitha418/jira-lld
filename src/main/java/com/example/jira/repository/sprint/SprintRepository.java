package com.example.jira.repository.sprint;

import com.example.jira.models.Sprint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SprintRepository extends JpaRepository<Sprint, Long> {
    @Query(value = "select * from sprint where name = :sprintName", nativeQuery = true)
    Optional<Sprint> getSprintByName(@Param("sprintName") String sprintName);
}
