package com.taskflow.dto.task;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.taskflow.domain.task.Task;
import com.taskflow.domain.task.TaskStatus;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class TaskResponse {

    private Long id;
    private String title;
    private String description;
    private String priority;
    private TaskStatus status;


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dueDate;
    private Long categoryId;

    @Builder
    public TaskResponse(Long id, String title, String description, String priority, TaskStatus status, LocalDateTime dueDate, Long categoryId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.status = status;
        this.dueDate = dueDate;
        this.categoryId = categoryId;
    }


    public static TaskResponse from(Task task) {
        return TaskResponse.builder()
                .id(task.getId())
                .title(task.getTitle())
                .description(task.getDescription())
                .priority(task.getPriority().toString())
                .status(task.getStatus())
                .dueDate(task.getDueDate() != null ? task.getDueDate() : null)
                .categoryId(task.getCategory().getId())
                .build();
    }

    public static List<TaskResponse> fromList(List<Task> tasks) {
        return tasks.stream().map(TaskResponse::from).toList();
    }


}
