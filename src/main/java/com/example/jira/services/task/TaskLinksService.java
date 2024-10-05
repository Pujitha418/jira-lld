package com.example.jira.services.task;

import com.example.jira.enums.LinkType;
import com.example.jira.models.task.Task;
import com.example.jira.models.task.TaskLinks;
import com.example.jira.repository.task.TaskLinksRepository;
import com.example.jira.repository.task.TaskRepository;
import org.springframework.stereotype.Service;

@Service
public class TaskLinksService {
    private TaskRepository taskRepository;
    private TaskLinksRepository taskLinksRepository;

    public TaskLinksService(TaskRepository taskRepository, TaskLinksRepository taskLinksRepository) {
        this.taskRepository = taskRepository;
        this.taskLinksRepository = taskLinksRepository;
    }

    public boolean addTaskLinks(Task source, Task destination, LinkType relation) {
        TaskLinks taskLinks = new TaskLinks();
        taskLinks.setSourceTask(source);
        taskLinks.setDestinationTask(destination);
        taskLinks.setLinkType(relation);
        taskLinksRepository.save(taskLinks);

        return true;
    }


}
