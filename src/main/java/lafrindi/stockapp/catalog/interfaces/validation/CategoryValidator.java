package lafrindi.stockapp.catalog.interfaces.validation;

import lafrindi.stockapp.catalog.application.dto.CategoryRequest;

public class CategoryValidator {

    public static void validate(CategoryRequest request) {
        if (request.name == null || request.name.isBlank()) {
            throw new IllegalArgumentException("Category name is required");
        }
    }
}
