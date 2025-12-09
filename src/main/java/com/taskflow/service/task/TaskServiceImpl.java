package com.taskflow.service.task;

import com.taskflow.domain.category.Category;
import com.taskflow.domain.category.CategoryRepository;
import com.taskflow.domain.task.Task;
import com.taskflow.domain.task.TaskRepository;
import com.taskflow.domain.user.User;
import com.taskflow.domain.user.UserRepository;
import com.taskflow.dto.task.TaskCreateRequest;
import com.taskflow.dto.task.TaskResponse;
import com.taskflow.dto.task.TaskUpdateRequest;
import com.taskflow.global.exception.CustomException;
import com.taskflow.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    @Override
    @Transactional
    public TaskResponse createTask(Long userId, TaskCreateRequest request) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new CustomException(ErrorCode.CATEGORY_NOT_FOUND));

        Task task = Task.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .dueDate(request.getDueDate())
                .priority(request.getPriority())
                .status(request.getStatus())
                .user(user)
                .category(category)
                .build();

        Task saved = taskRepository.save(task);

        return TaskResponse.from(saved);
    }

    @Override
    public List<TaskResponse> getTasksByUser(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        return TaskResponse.fromList(taskRepository.findByUserAndDeletedFalse(user));
    }

    @Override
    public List<TaskResponse> getTasksByCategory(Long categoryId) {

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CustomException(ErrorCode.CATEGORY_NOT_FOUND));
        return TaskResponse.fromList(taskRepository.findByCategoryAndDeletedFalse(category));
    }

    @Override
    public TaskResponse getTask(Long taskId) {

        Task task = taskRepository.findByIdAndDeletedFalse(taskId)
                .orElseThrow(() -> new CustomException(ErrorCode.TASK_NOT_FOUND));

        return TaskResponse.from(task);
    }

    @Override
    @Transactional
    public TaskResponse updateTask(Long taskId, TaskUpdateRequest request) {

        Task task = taskRepository.findByIdAndDeletedFalse(taskId)
                .orElseThrow(() -> new CustomException(ErrorCode.TASK_NOT_FOUND));
        task.updateTask(
                request.getTitle(),
                request.getDescription(),
                request.getPriority(),
                request.getStatus(),
                request.getDueDate()
        );

        return TaskResponse.from(task);
    }

    @Override
    @Transactional
    public void deleteTask(Long taskId) {

        Task task = taskRepository.findByIdAndDeletedFalse(taskId)
                .orElseThrow(() -> new CustomException(ErrorCode.TASK_NOT_FOUND));

        task.softDelete();
    }
}
