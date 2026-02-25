package lafrindi.stockapp.catalog.domain.service;

import lafrindi.stockapp.catalog.domain.model.Category;
import lafrindi.stockapp.catalog.domain.repository.CategoryRepository;
import lafrindi.stockapp.catalog.domain.valueObject.CategoryId;

import java.util.List;
import java.util.Optional;

public class CategoryDomainService {

    private final CategoryRepository categoryRepository;

    public CategoryDomainService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category createCategory(String name, String description, CategoryId parentId) {

        Category category = new Category(
                CategoryId.newId(),
                name,
                description,
                parentId
        );

        return categoryRepository.save(category);
    }

    public Optional<Category> findById(CategoryId id) {
        return categoryRepository.findById(id);
    }

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Category updateCategory(CategoryId id, String name, String description, CategoryId parentId) {
        Optional<Category> existingCategory = categoryRepository.findById(id);
        if (existingCategory.isEmpty()) {
            throw new IllegalArgumentException("Category not found with id: " + id.getValue());
        }

        Category category = existingCategory.get();
        category.rename(name);
        category.updateDescription(description);
        category.changeParent(parentId);

        return categoryRepository.save(category);
    }

    public void deleteCategory(CategoryId id) {
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isEmpty()) {
            throw new IllegalArgumentException("Category not found with id: " + id.getValue());
        }
        categoryRepository.delete(category.get());
    }
}
