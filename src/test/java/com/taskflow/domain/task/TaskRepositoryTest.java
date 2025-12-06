package com.taskflow.domain.task;

import com.taskflow.domain.category.Category;
import com.taskflow.domain.category.CategoryRepository;
import com.taskflow.domain.user.User;
import com.taskflow.domain.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class TaskRepositoryTest {

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Test
    void save_and_find_task() {
        User user = User.builder()
                .name("testUser")
                .email("test@test.com")
                .password("1234")
                .build();
        userRepository.save(user);

        Category category = Category.builder()
                .name("testCategory")
                .user(user)
                .build();
        categoryRepository.save(category);

        Task task = Task.builder().title("testTitle")
                .priority(1)
                .status("TODO")
                .user(user)
                .category(category)
                .deleted(false)
                .build();

        Task saved = taskRepository.save(task);

        Task found = taskRepository.findById(saved.getId()).orElse(null);

        assertThat(found).isNotNull();
        assertThat(found.getTitle()).isEqualTo("testTitle");

    }
}