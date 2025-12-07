package com.taskflow.domain.category;

import com.taskflow.domain.user.User;
import com.taskflow.domain.user.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class CategoryRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Test
    @DisplayName("Category 저장 및 조회 테스트")
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

        // when
        Category found = categoryRepository.findById(category.getId()).orElse(null);

        // then
        assertThat(found).isNotNull();
        assertThat(found.getName()).isEqualTo("testCategory");
        assertThat(found.getUser().getId()).isEqualTo(user.getId());

    }

    @Test
    @DisplayName("User 기준 Category 목록 조회 테스트")
    public void find_categories_by_user() throws Exception {
        // given
        User user = User.builder()
                .name("testUser")
                .email("test@test.com")
                .password("1234")
                .build();
        userRepository.save(user);

        Category category1 = Category.builder()
                .name("testCategory1")
                .user(user)
                .build();

        Category category2 = Category.builder()
                .name("testCategory2")
                .user(user)
                .build();

        categoryRepository.save(category1);
        categoryRepository.save(category2);

        // when
        List<Category> categories = categoryRepository.findByUser(user);

        // then
        assertThat(categories).hasSize(2);
        assertThat(categories.get(0).getUser().getId()).isEqualTo(user.getId());
    }

}