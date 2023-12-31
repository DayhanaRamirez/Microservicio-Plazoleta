package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.api.ICategoryServicePort;
import com.pragma.powerup.domain.model.Category;
import com.pragma.powerup.domain.spi.ICategoryPersistencePort;
import com.pragma.powerup.infrastructure.client.IAccountFeignClient;

import java.util.List;

public class CategoryUseCase implements ICategoryServicePort {
    private final ICategoryPersistencePort categoryPersistencePort;
    private final IAccountFeignClient accountFeignClient;

    public CategoryUseCase(ICategoryPersistencePort categoryPersistencePort, IAccountFeignClient accountFeignClient ){
        this.categoryPersistencePort = categoryPersistencePort;
        this.accountFeignClient = accountFeignClient;
    }

    @Override
    public void saveCategory(Category category) {
        categoryPersistencePort.saveCategory(category);

    }

    @Override
    public List<Category> getAllCategories() {
        return categoryPersistencePort.getAllCategories();
    }

    @Override
    public Category getCategory(Long id) {
        return categoryPersistencePort.getCategory(id);
    }

    @Override
    public void updateCategory(Category category) {
        categoryPersistencePort.updateCategory(category);
    }

    @Override
    public void deleteCategory(Long id) {
        categoryPersistencePort.deleteCategory(id);
    }
}
