package com.taskflow.dto.category;

import com.taskflow.domain.category.Category;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class CategoryResponse {

    private Long id;
    private String name;

    @Builder
    public CategoryResponse(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static CategoryResponse from(Category category) {
        return CategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }

    public static List<CategoryResponse> fromList(List<Category> categories) {
        return categories.stream().map(CategoryResponse::from).toList();
    }
}
