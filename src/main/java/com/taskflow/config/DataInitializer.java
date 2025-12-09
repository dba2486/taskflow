package com.taskflow.config;

import com.taskflow.domain.category.Category;
import com.taskflow.domain.category.CategoryRepository;
import com.taskflow.domain.task.Task;
import com.taskflow.domain.task.TaskRepository;
import com.taskflow.domain.user.User;
import com.taskflow.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

@Component
@RequiredArgsConstructor
@Profile("dev")
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final TaskRepository taskRepository;


    @Override
    public void run(String... args) throws Exception {
        if (userRepository.count() > 0) {
            System.out.println("초기 데이터 이미 존재 -> 데이터 삽입 생략");
            return;
        }

        System.out.println("더미 데이터 삽입 시작.");

        // ------------- User 생성 ---------------
        User user1 = User.builder().name("김철수").email("kim@example.com").password("1234").build();
        User user2 = User.builder().name("이영희").email("lee@example.com").password("1357").build();

        userRepository.saveAll(Arrays.asList(user1, user2));

        // ------------- Category 생성 ---------------
        Category category1 = Category.builder().name("공부").user(user1).build();
        Category category2 = Category.builder().name("운동").user(user1).build();

        Category category3 = Category.builder().name("게임").user(user2).build();
        Category category4 = Category.builder().name("여행").user(user2).build();

        categoryRepository.saveAll(Arrays.asList(category1, category2, category3, category4));

        // ------------- Task 생성 ---------------
        Task task1 = Task.builder().title("과학공부").description("가계도 공부").priority(2).status("TODO")
                .dueDate(LocalDateTime.now().plusDays(1)).user(user1).category(category1).build();
        Task task2 = Task.builder().title("수학공부").description("미분 공부").priority(1).status("IN_PROGRESS")
                .dueDate(LocalDateTime.now().plusDays(1)).user(user1).category(category1).build();

        Task task3 = Task.builder().title("웨이트 트레이닝").description("등운동").priority(2).status("DONE")
                .dueDate(LocalDateTime.now()).user(user1).category(category2).build();
        Task task4 = Task.builder().title("축구").description("축구 대회준비").priority(3).status("TODO")
                .dueDate(LocalDateTime.now().plusDays(7)).user(user1).category(category2).build();

        Task task5 = Task.builder().title("메이플").description("보스돌이").priority(3).status("TODO")
                .dueDate(LocalDateTime.now().plusDays(3)).user(user2).category(category3).build();
        Task task6 = Task.builder().title("LOL").description("칼바람").priority(3).status("TODO")
                .dueDate(LocalDateTime.now().plusDays(4)).user(user2).category(category3).build();

        Task task7 = Task.builder().title("국내여행").description("삼척여행").priority(3).status("TODO")
                .dueDate(LocalDateTime.now().plusDays(30)).user(user2).category(category4).build();
        Task task8 = Task.builder().title("일본여행").description("도쿄여행").priority(3).status("TODO")
                .dueDate(LocalDateTime.now().plusDays(40)).user(user2).category(category4).build();

        taskRepository.saveAll(Arrays.asList(task1, task2, task3, task4, task5, task6, task7, task8));

        System.out.println("더미 데이터 삽입 완료.");
    }
}
