package com.pragma.powerup.application.handler;

import com.pragma.powerup.application.dto.request.CategoryRequestDto;
import com.pragma.powerup.application.dto.request.CategoryUpdateRequestDto;
import com.pragma.powerup.application.dto.response.CategoryResponseDto;

import java.util.List;

public interface ICategoryHandler {
    void saveCategory(CategoryRequestDto categoryRequestDto);

    List<CategoryResponseDto> getAllCategories();

    CategoryResponseDto getCategory(Long id);

    void updateCategory(CategoryUpdateRequestDto categoryUpdateRequestDto);

    void deleteCategory(Long id);
}
