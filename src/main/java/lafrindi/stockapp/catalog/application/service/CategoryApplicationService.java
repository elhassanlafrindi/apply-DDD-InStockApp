package lafrindi.stockapp.catalog.application.service;

import lafrindi.stockapp.catalog.application.dto.CategoryRequest;
import lafrindi.stockapp.catalog.application.dto.CategoryResponse;

public interface CategoryApplicationService {
    CategoryResponse create(CategoryRequest request);
}