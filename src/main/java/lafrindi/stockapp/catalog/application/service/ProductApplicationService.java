package lafrindi.stockapp.catalog.application.service;

import lafrindi.stockapp.catalog.application.dto.ProductRequest;
import lafrindi.stockapp.catalog.application.dto.ProductResponse;

public interface ProductApplicationService {
    ProductResponse create(ProductRequest request);
}
