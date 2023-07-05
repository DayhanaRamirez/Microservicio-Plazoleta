package com.pragma.powerup.application.handler.impl;

import com.pragma.powerup.application.dto.request.CategoryRequestDto;
import com.pragma.powerup.application.dto.request.CategoryUpdateRequestDto;
import com.pragma.powerup.application.dto.response.CategoryResponseDto;
import com.pragma.powerup.application.handler.ICategoryHandler;
import com.pragma.powerup.application.mapper.IObjectRequestMapper;
import com.pragma.powerup.application.mapper.IObjectResponseMapper;
import com.pragma.powerup.domain.api.ICategoryServicePort;
import com.pragma.powerup.domain.model.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoryHandler implements ICategoryHandler {

    private final ICategoryServicePort categoryServicePort;
    private final IObjectRequestMapper objectRequestMapper;
    private final IObjectResponseMapper objectResponseMapper;

    @Override
    public void saveCategory(CategoryRequestDto categoryRequestDto) {
        categoryServicePort.saveCategory(objectRequestMapper.categoryDtoToCategory(categoryRequestDto));

    }

    @Override
    public List<CategoryResponseDto> getAllCategories() {
        return objectResponseMapper.categoryToCategoryDtoList(categoryServicePort.getAllCategories());    }

    @Override
    public CategoryResponseDto getCategory(Long id) {
        return objectResponseMapper.categoryToCategoryDto(categoryServicePort.getCategory(id));
    }

    @Override
    public void updateCategory(CategoryUpdateRequestDto categoryUpdateRequestDto) {
        Category category = categoryServicePort.getCategory(categoryUpdateRequestDto.getId());
        category.setName(category.getName());
        category.setDescription(categoryUpdateRequestDto.getDescription());

        categoryServicePort.updateCategory(category);

    }

    @Override
    public void deleteCategory(Long id) {
        categoryServicePort.deleteCategory(id);
    }
}
