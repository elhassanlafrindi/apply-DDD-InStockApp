package lafrindi.stockapp.catalog.application.service;

import lafrindi.stockapp.catalog.application.dto.ProductRequest;
import lafrindi.stockapp.catalog.application.dto.ProductResponse;

import java.util.List;
import java.util.UUID;

public interface ProductApplicationService {
    ProductResponse create(ProductRequest request);
    ProductResponse findById(UUID id);
    List<ProductResponse> findAll();
    ProductResponse update(UUID id, ProductRequest request);
    void delete(UUID id);
}