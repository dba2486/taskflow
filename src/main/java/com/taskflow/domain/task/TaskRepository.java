package com.taskflow.domain.task;

import com.taskflow.domain.category.Category;
import com.taskflow.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {

    Optional<Task> findByIdAndDeletedFalse(Long id);

    List<Task> findByUserAndDeletedFalse(User user);

    List<Task> findByCategoryAndDeletedFalse(Category category);

}
