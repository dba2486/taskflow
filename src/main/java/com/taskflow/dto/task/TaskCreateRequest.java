package com.taskflow.dto.task;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class TaskCreateRequest {

    private String title;
    private String description;
    private LocalDateTime dueDate;
    private Integer priority;
    private String status;
    private Long categoryId;


}
