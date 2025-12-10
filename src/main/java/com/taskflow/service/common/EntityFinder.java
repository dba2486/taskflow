package com.taskflow.service.common;

import com.taskflow.domain.category.Category;
import com.taskflow.domain.category.CategoryRepository;
import com.taskflow.domain.task.Task;
import com.taskflow.domain.task.TaskRepository;
import com.taskflow.domain.user.User;
import com.taskflow.domain.user.UserRepository;
import com.taskflow.global.exception.CustomException;
import com.taskflow.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EntityFinder {

    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final TaskRepository taskRepository;


    /**
     * User 조회 (없을 시 USER_NOT_FOUND)
     */
    public User getUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
    }

    /**
     * Category 조회 (없을 시 USER_NOT_FOUND)
     */
    public Category getCategory(Long categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CustomException(ErrorCode.CATEGORY_NOT_FOUND));
    }

    /**
     * Task 조회 (없을 시 USER_NOT_FOUND)
     */
    public Task getTask(Long taskId) {
        return taskRepository.findByIdAndDeletedFalse(taskId)
                .orElseThrow(() -> new CustomException(ErrorCode.TASK_NOT_FOUND));
    }

}
