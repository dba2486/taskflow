package com.taskflow.dto.task;

import com.taskflow.domain.task.Task;
import lombok.Getter;

@Getter
public class TaskResponse {

    private Long id;
    private String title;
    private String description;
    private String priority;
    private String status;
    private String dueDate;
    private Long categoryId;

    public TaskResponse(Task task) {
        this.id = task.getId();
        this.title = task.getTitle();
        this.description = task.getDescription();
        this.priority = task.getPriority().toString();
        this.status = task.getStatus();
        this.dueDate = task.getDueDate() != null ? task.getDueDate().toString() : null;
        this.categoryId = task.getCategory().getId();
    }

}
