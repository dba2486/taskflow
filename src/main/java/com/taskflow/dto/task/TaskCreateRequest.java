package com.taskflow.dto.task;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class TaskCreateRequest {

    @NotBlank(message = "업무 제목은 필수입니다.")
    private String title;

    private String description;

    @NotNull(message = "마감기한은 필수입니다.")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dueDate;

    @NotBlank(message = "우선순위는 필수입니다.")
    private Integer priority;

    @NotBlank(message = "업무 상태는 필수입니다.")
    private String status;

    @NotNull(message = "카테고리 ID는 필수입니다.")
    private Long categoryId;


}
