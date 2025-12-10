package com.taskflow.service.category;

import com.taskflow.domain.category.Category;
import com.taskflow.domain.category.CategoryRepository;
import com.taskflow.domain.user.User;
import com.taskflow.dto.category.CategoryRequest;
import com.taskflow.dto.category.CategoryResponse;
import com.taskflow.service.common.EntityFinder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final EntityFinder finder;

    @Override
    @Transactional
    public CategoryResponse createCategory(Long userId, CategoryRequest request) {

        User user = finder.getUser(userId);

        Category category = Category.builder()
                .name(request.getName())
                .user(user)
                .build();

        Category saved = categoryRepository.save(category);

        return CategoryResponse.from(saved);
    }

    @Override
    public List<CategoryResponse> getCategories(Long userId) {

        User user = finder.getUser(userId);

        return CategoryResponse.fromList(categoryRepository.findByUser(user));
    }

    @Override
    public CategoryResponse getCategory(Long categoryId) {

        Category category = finder.getCategory(categoryId);

        return CategoryResponse.from(category);
    }

    @Override
    @Transactional
    public CategoryResponse updateCategory(Long categoryId, CategoryRequest request) {

        Category category = finder.getCategory(categoryId);

        category.updateName(request.getName());
        return CategoryResponse.from(category);
    }

    @Override
    @Transactional
    public void deleteCategory(Long categoryId) {

        Category category = finder.getCategory(categoryId);

        categoryRepository.delete(category);
    }
}
