package com.taskflow.service.category;

import com.taskflow.domain.category.Category;
import com.taskflow.domain.category.CategoryRepository;
import com.taskflow.domain.user.User;
import com.taskflow.domain.user.UserRepository;
import com.taskflow.dto.category.CategoryCreateRequest;
import com.taskflow.dto.category.CategoryResponse;
import com.taskflow.global.exception.CustomException;
import com.taskflow.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public CategoryResponse createCategory(Long userId, CategoryCreateRequest request) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        Category category = Category.builder()
                .name(request.getName())
                .user(user)
                .build();

        Category saved = categoryRepository.save(category);

        return new CategoryResponse(saved);
    }

    @Override
    public List<CategoryResponse> getCategories(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        return categoryRepository.findByUser(user).stream().map(CategoryResponse::new).toList();
    }

    @Override
    public CategoryResponse getCategory(Long categoryId) {

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CustomException(ErrorCode.CATEGORY_NOT_FOUND));

        return new CategoryResponse(category);
    }

    @Override
    @Transactional
    public CategoryResponse updateCategory(Long categoryId, String newName) {

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CustomException(ErrorCode.CATEGORY_NOT_FOUND));

        category.updateName(newName);
        return new CategoryResponse(category);
    }

    @Override
    @Transactional
    public void deleteCategory(Long categoryId) {

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CustomException(ErrorCode.CATEGORY_NOT_FOUND));

        categoryRepository.delete(category);
    }
}
