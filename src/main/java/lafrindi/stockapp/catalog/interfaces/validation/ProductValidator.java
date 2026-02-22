package lafrindi.stockapp.catalog.interfaces.validation;

import lafrindi.stockapp.catalog.application.dto.ProductRequest;

public class ProductValidator {

    public static void validate(ProductRequest request) {
        if (request.name == null || request.name.isBlank()) {
            throw new IllegalArgumentException("Name required");
        }
    }
}