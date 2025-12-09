package com.taskflow.service.task;


import com.taskflow.dto.task.TaskCreateRequest;
import com.taskflow.dto.task.TaskResponse;
import com.taskflow.dto.task.TaskUpdateRequest;

import java.util.List;

public interface TaskService {

    /**
     * 새로운 task 생성
     */
    TaskResponse createTask(Long userId, TaskCreateRequest request);

    /**
     * 특정 사용자의 task 목록 조회
     */
    List<TaskResponse> getTasksByUser(Long userId);

    /**
     * 특정 카테고리의 task 목록 조회
     */
    List<TaskResponse> getTasksByCategory(Long categoryId);

    /**
     * task 단건 조회
     */
    TaskResponse getTask(Long taskId);

    /**
     * task 수정
     */
    TaskResponse updateTask(Long taskId, TaskUpdateRequest request);

    /**
     * task 삭제 (soft delete)
     */
    void deleteTask(Long taskId);
}
