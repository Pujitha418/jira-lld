package com.example.jira.services;

import com.example.jira.dto.CreateProjectRequestDto;
import com.example.jira.models.Project;
import com.example.jira.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProjectService {
    private ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public Optional<Project> getProject(Long projectId) {
        return projectRepository.findById(projectId);
    }

    public boolean createProject(CreateProjectRequestDto createProjectRequestDto) {
        Project project = new Project();
        project.setName(createProjectRequestDto.getName());
        project.setTaskIdPrefix(createProjectRequestDto.getTaskIdPrefix());
        projectRepository.save(project);

        return true;
    }
}
