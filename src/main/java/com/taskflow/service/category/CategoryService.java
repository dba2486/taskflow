package com.taskflow.service.category;

import com.taskflow.dto.category.CategoryRequest;
import com.taskflow.dto.category.CategoryResponse;

import java.util.List;

public interface CategoryService {

    /**
     * 카테고리 생성
     */
    CategoryResponse createCategory(Long userId, CategoryRequest request);

    /**
     * 특정 사용자의 카테고리 목록 조회
     */
    List<CategoryResponse> getCategories(Long userId);

    /**
     * 카테고리 단건 조회
     */
    CategoryResponse getCategory(Long categoryId);

    /**
     * 카테고리 이름 수정
     */
    CategoryResponse updateCategory(Long categoryId, CategoryRequest request);

    /**
     * 카테고리 삭제
     */
    void deleteCategory(Long categoryId);
}
