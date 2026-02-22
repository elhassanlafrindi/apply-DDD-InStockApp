package lafrindi.stockapp.catalog.domain.service;

import lafrindi.stockapp.catalog.domain.model.Category;
import lafrindi.stockapp.catalog.domain.repository.CategoryRepository;
import lafrindi.stockapp.catalog.domain.valueObject.CategoryId;

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
}
