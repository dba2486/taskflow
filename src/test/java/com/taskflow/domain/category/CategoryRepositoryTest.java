package com.taskflow.domain.category;

import com.taskflow.domain.user.User;
import com.taskflow.domain.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class CategoryRepositoryTest {

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

        Category saved = categoryRepository.save(category);

        Category found = categoryRepository.findById(saved.getId()).orElse(null);

        assertThat(found).isNotNull();
        assertThat(found.getName()).isEqualTo("testCategory");

    }

}