package lafrindi.stockapp.catalog.interfaces.validation;

import lafrindi.stockapp.catalog.application.dto.ProductRequest;

import java.util.UUID;

public class ProductValidator {

    public static void validate(ProductRequest request) {
        if (request.name == null || request.name.isBlank()) {
            throw new IllegalArgumentException("Product name is required");
        }
        
        if (request.name.length() > 100) {
            throw new IllegalArgumentException("Product name must be less than 100 characters");
        }
        
        if (request.sku == null || request.sku.isBlank()) {
            throw new IllegalArgumentException("SKU is required");
        }
        
        if (request.sku.length() > 50) {
            throw new IllegalArgumentException("SKU must be less than 50 characters");
        }
        
        if (request.price == null || request.price.compareTo(java.math.BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Price must be positive");
        }
        
        if (request.stockQuantity == null || request.stockQuantity < 0) {
            throw new IllegalArgumentException("Stock quantity must be non-negative");
        }
        
        if (request.categoryId == null) {
            throw new IllegalArgumentException("Category ID is required");
        }
        
        if (request.currency == null || request.currency.isBlank()) {
            throw new IllegalArgumentException("Currency is required");
        }
    }
    
    public static void validateUpdate(UUID id, ProductRequest request) {
        if (id == null) {
            throw new IllegalArgumentException("Product ID is required");
        }
        
        validate(request);
    }
}