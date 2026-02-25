package lafrindi.stockapp.catalog.interfaces.validation;

import lafrindi.stockapp.catalog.application.dto.CategoryRequest;

import java.util.UUID;

public class CategoryValidator {

    public static void validate(CategoryRequest request) {
        if (request.name == null || request.name.isBlank()) {
            throw new IllegalArgumentException("Category name is required");
        }
        
        if (request.name.length() > 100) {
            throw new IllegalArgumentException("Category name must be less than 100 characters");
        }
        
        if (request.description != null && request.description.length() > 500) {
            throw new IllegalArgumentException("Category description must be less than 500 characters");
        }
    }
    
    public static void validateUpdate(UUID id, CategoryRequest request) {
        if (id == null) {
            throw new IllegalArgumentException("Category ID is required");
        }
        
        validate(request);
    }
}