package com.example.jira.services.sprint;

import com.example.jira.models.Sprint;
import com.example.jira.repository.sprint.SprintRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SprintService {
    private SprintRepository sprintRepository;

    public SprintService(SprintRepository sprintRepository) {
        this.sprintRepository = sprintRepository;
    }

    public Optional<Sprint> getSprint(String sprintName) {
        return sprintRepository.getSprintByName(sprintName);
    }
}
