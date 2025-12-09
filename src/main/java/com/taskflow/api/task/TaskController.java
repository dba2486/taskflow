package com.taskflow.api.task;

import com.taskflow.dto.task.TaskCreateRequest;
import com.taskflow.dto.task.TaskResponse;
import com.taskflow.dto.task.TaskUpdateRequest;
import com.taskflow.global.response.ApiResponse;
import com.taskflow.service.task.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    /**
     * 새로운 task 생성
     */
    @PostMapping("/users/{userId}/tasks")
    public ApiResponse<TaskResponse> createTask(@PathVariable Long userId, @Valid @RequestBody TaskCreateRequest request) {
        return ApiResponse.success(taskService.createTask(userId, request));
    }


    /**
     * 특정 사용자의 task 목록 조회
     */
    @GetMapping("/users/{userId}/tasks")
    public ApiResponse<List<TaskResponse>> getTasksByUser(@PathVariable Long userId) {
        return ApiResponse.success(taskService.getTasksByUser(userId));
    }

    /**
     * 특정 카테고리의 task 목록 조회
     */
    @GetMapping("/categories/{categoryId}/tasks")
    public ApiResponse<List<TaskResponse>> getTasksByCategory(@PathVariable Long categoryId) {
        return ApiResponse.success(taskService.getTasksByCategory(categoryId));
    }


    /**
     * task 단건 조회
     */
    @GetMapping("tasks/{taskId}")
    public ApiResponse<TaskResponse> getTask(@PathVariable Long taskId) {
        return ApiResponse.success(taskService.getTask(taskId));
    }


    /**
     * task 수정
     */
    @PatchMapping("tasks/{taskId}")
    public ApiResponse<TaskResponse> updateTask(@PathVariable Long taskId, @Valid @RequestBody TaskUpdateRequest request) {
        return ApiResponse.success(taskService.updateTask(taskId, request));
    }


    /**
     * task 삭제 (soft delete)
     */
    @DeleteMapping("tasks/{taskId}")
    public ApiResponse<TaskResponse> deleteTask(@PathVariable Long taskId) {
        taskService.deleteTask(taskId);
        return ApiResponse.success(null);
    }
}
