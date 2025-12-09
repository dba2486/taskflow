package com.taskflow.domain.task;

import com.taskflow.domain.category.Category;
import com.taskflow.domain.category.CategoryRepository;
import com.taskflow.domain.user.User;
import com.taskflow.domain.user.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class TaskRepositoryTest {

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Test
    @DisplayName("Task 저장 및 조회 테스트")
    void save_and_find_task() {
        // given
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
                .description("test description.")
                .dueDate(LocalDateTime.now().plusDays(1))
                .priority(1)
                .status("TODO")
                .user(user)
                .category(category)
                .deleted(false)
                .build();
        taskRepository.save(task);

        // when
        Task found = taskRepository.findById(task.getId()).orElse(null);

        // then
        assertThat(found).isNotNull();
        assertThat(found.getTitle()).isEqualTo("testTitle");
        assertThat(found.getUser().getId()).isEqualTo(user.getId());
        assertThat(found.getCategory().getId()).isEqualTo(category.getId());

    }

    @Test
    @DisplayName("특정 User의 Task 목록 조회 테스트")
    public void find_tasks_by_user() throws Exception {
        // given
        User user = User.builder()
                .name("userA")
                .email("a@test.com")
                .password("1234")
                .build();
        userRepository.save(user);

        Category category = Category.builder()
                .name("개발")
                .user(user)
                .build();
        categoryRepository.save(category);

        Task task1 = Task.builder()
                .title("first task")
                .status("TODO")
                .priority(1)
                .user(user)
                .category(category)
                .deleted(false)
                .build();

        Task task2 = Task.builder()
                .title("second task")
                .status("IN_PROGRESS")
                .priority(2)
                .user(user)
                .category(category)
                .deleted(false)
                .build();

        taskRepository.save(task1);
        taskRepository.save(task2);

        // when
        List<Task> tasks = taskRepository.findByUserAndDeletedFalse(user);

        // then
        assertThat(tasks).hasSize(2);
        assertThat(tasks.get(0).getUser()).isEqualTo(user);
    }

    @Test
    @DisplayName("특정 Category의 Task 목록 조회 테스트")
    public void find_tasks_by_category() throws Exception {
        // given
        User user = User.builder()
                .name("userA")
                .email("a@test.com")
                .password("1234")
                .build();
        userRepository.save(user);

        Category category = Category.builder()
                .name("개발")
                .user(user)
                .build();
        categoryRepository.save(category);

        Task task1 = Task.builder()
                .title("first task")
                .status("TODO")
                .priority(1)
                .user(user)
                .category(category)
                .deleted(false)
                .build();

        Task task2 = Task.builder()
                .title("second task")
                .status("IN_PROGRESS")
                .priority(2)
                .user(user)
                .category(category)
                .deleted(false)
                .build();

        taskRepository.save(task1);
        taskRepository.save(task2);

        // when
        List<Task> tasks = taskRepository.findByCategoryAndDeletedFalse(category);

        // then
        assertThat(tasks).hasSize(2);
        assertThat(tasks.get(0).getCategory()).isEqualTo(category);
    }
}