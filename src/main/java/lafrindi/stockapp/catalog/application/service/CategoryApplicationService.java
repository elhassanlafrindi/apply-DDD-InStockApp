package lafrindi.stockapp.catalog.application.service;

import lafrindi.stockapp.catalog.application.dto.CategoryRequest;
import lafrindi.stockapp.catalog.application.dto.CategoryResponse;

import java.util.List;
import java.util.UUID;

public interface CategoryApplicationService {
    CategoryResponse create(CategoryRequest request);
    CategoryResponse findById(UUID id);
    List<CategoryResponse> findAll();
    CategoryResponse update(UUID id, CategoryRequest request);
    void delete(UUID id);
}