package com.taskflow.api.category;

import com.taskflow.dto.category.CategoryRequest;
import com.taskflow.dto.category.CategoryResponse;
import com.taskflow.global.response.ApiResponse;
import com.taskflow.service.category.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class CategoryController {

    private final CategoryService categoryService;

    /**
     * 카테고리 생성
     */
    @PostMapping("/users/{userId}/categories")
    public ApiResponse<CategoryResponse> createCategory(@PathVariable Long userId, @Valid @RequestBody CategoryRequest request) {
        return ApiResponse.success(categoryService.createCategory(userId, request));
    }

    /**
     * 특정 사용자의 카테고리 목록 조회
     */
    @GetMapping("/users/{userId}/categories")
    public ApiResponse<List<CategoryResponse>> getCategories(@PathVariable Long userId) {
        return ApiResponse.success(categoryService.getCategories(userId));
    }

    /**
     * 카테고리 단건 조회
     */
    @GetMapping("/categories/{categoryId}")
    public ApiResponse<CategoryResponse> getCategory(@PathVariable Long categoryId) {
        return ApiResponse.success(categoryService.getCategory(categoryId));
    }

    /**
     * 카테고리 이름 수정
     */
    @PatchMapping("/categories/{categoryId}")
    public ApiResponse<CategoryResponse> updateCategory(@PathVariable Long categoryId, @Valid @RequestBody CategoryRequest request) {
        return ApiResponse.success(categoryService.updateCategory(categoryId, request));
    }

    /**
     * 카테고리 삭제
     */
    @DeleteMapping("/categories/{categoryId}")
    public ApiResponse<CategoryResponse> deleteCategory(@PathVariable Long categoryId) {
        categoryService.deleteCategory(categoryId);
        return ApiResponse.success(null);
    }


}
