package com.pragma.powerup.infrastructure.out.jpa.adapter;

import com.pragma.powerup.domain.model.Category;
import com.pragma.powerup.domain.spi.ICategoryPersistencePort;
import com.pragma.powerup.infrastructure.exception.CategoryNotFoundException;
import com.pragma.powerup.infrastructure.out.jpa.entity.CategoryEntity;
import com.pragma.powerup.infrastructure.out.jpa.mapper.ICategoryEntityMapper;
import com.pragma.powerup.infrastructure.out.jpa.repository.ICategoryRepository;
import lombok.RequiredArgsConstructor;

import javax.swing.undo.CannotRedoException;
import java.util.List;

@RequiredArgsConstructor
public class CategoryJpaAdapter implements ICategoryPersistencePort {

    private final ICategoryRepository categoryRepository;
    private final ICategoryEntityMapper categoryEntityMapper;


    @Override
    public void saveCategory(Category category) {
        CategoryEntity categoryEntity = categoryEntityMapper.categoryToEntity(category);
        categoryRepository.save(categoryEntity);

    }

    @Override
    public List<Category> getAllCategories() {
        List<CategoryEntity> categoryEntityList = categoryRepository.findAll();
        if(categoryEntityList.isEmpty()){
            throw new CategoryNotFoundException();
        }
        return categoryEntityMapper.entitiesToCategoryList(categoryEntityList);
    }

    @Override
    public Category getCategory(Long id) {
        return categoryEntityMapper.entityToCategory(categoryRepository.findById(id)
                .orElseThrow(CannotRedoException::new));
    }

    @Override
    public void updateCategory(Category category) {
        saveCategory(category);
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}
