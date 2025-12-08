package com.taskflow.dto.task;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class TaskUpdateRequest {

    private String title;
    private String description;
    private Integer priority;
    private String status;
    private LocalDateTime dueDate;
}
