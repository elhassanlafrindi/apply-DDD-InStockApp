package lafrindi.stockapp.catalog.domain.model;

import lafrindi.stockapp.catalog.domain.valueObject.CategoryId;
import lombok.Getter;

@Getter
public class Category {

    private final CategoryId categoryId;
    private String name;
    private String description;
    private CategoryId parentCategoryId;

    public Category(CategoryId categoryId, String name, String description, CategoryId parentCategoryId) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Category name cannot be empty");
        }

        this.categoryId = categoryId;
        this.name = name;
        this.description = description;
        this.parentCategoryId = parentCategoryId;
    }

    // 🔹 Business methods

    public void rename(String newName) {
        if (newName == null || newName.isBlank()) {
            throw new IllegalArgumentException("Category name cannot be empty");
        }
        this.name = newName;
    }

    public void changeParent(CategoryId newParentId) {
        this.parentCategoryId = newParentId;
    }
}
