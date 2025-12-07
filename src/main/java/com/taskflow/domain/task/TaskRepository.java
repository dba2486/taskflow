package com.taskflow.domain.task;

import com.taskflow.domain.category.Category;
import com.taskflow.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {


    List<Task> findByUser(User user);

    List<Task> findByCategory(Category category);
}
