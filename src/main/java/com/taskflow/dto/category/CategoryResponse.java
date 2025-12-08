package com.taskflow.dto.category;

import com.taskflow.domain.category.Category;
import lombok.Getter;

@Getter
public class CategoryResponse {

    private Long id;
    private String name;

    public CategoryResponse(Category category) {
        this.id = category.getId();
        this.name = category.getName();
    }
}
