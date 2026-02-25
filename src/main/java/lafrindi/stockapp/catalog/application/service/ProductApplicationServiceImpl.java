package lafrindi.stockapp.catalog.application.service;

import lafrindi.stockapp.catalog.application.dto.ProductRequest;
import lafrindi.stockapp.catalog.application.dto.ProductResponse;
import lafrindi.stockapp.catalog.application.mapper.ProductMapper;
import lafrindi.stockapp.catalog.domain.model.Product;
import lafrindi.stockapp.catalog.domain.service.ProductDomainService;
import lafrindi.stockapp.catalog.domain.valueObject.CategoryId;
import lafrindi.stockapp.catalog.domain.valueObject.Price;
import lafrindi.stockapp.catalog.domain.valueObject.ProductId;
import lafrindi.stockapp.catalog.domain.valueObject.Sku;
import lafrindi.stockapp.catalog.interfaces.validation.ProductValidator;
import org.springframework.stereotype.Service;

import java.util.Currency;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProductApplicationServiceImpl implements ProductApplicationService {

    private final ProductDomainService domainService;

    public ProductApplicationServiceImpl(ProductDomainService domainService) {
        this.domainService = domainService;
    }

    @Override
    public ProductResponse create(ProductRequest request) {
        ProductValidator.validate(request);

        Product product = domainService.createProduct(
                request.name,
                new Sku(request.sku),
                new Price(request.price, Currency.getInstance(request.currency)),
                request.stockQuantity,
                new CategoryId(request.categoryId)
        );

        return ProductMapper.toResponse(product);
    }

    @Override
    public ProductResponse findById(UUID id) {
        return domainService.findById(new ProductId(id))
                .map(ProductMapper::toResponse)
                .orElseThrow(() -> new IllegalArgumentException("Product not found with id: " + id));
    }

    @Override
    public List<ProductResponse> findAll() {
        return domainService.findAll().stream()
                .map(ProductMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ProductResponse update(UUID id, ProductRequest request) {
        ProductValidator.validateUpdate(id, request);

        Product product = domainService.updateProduct(
                new ProductId(id),
                request.name,
                new Sku(request.sku),
                new Price(request.price, Currency.getInstance(request.currency)),
                request.stockQuantity,
                new CategoryId(request.categoryId)
        );

        return ProductMapper.toResponse(product);
    }

    @Override
    public void delete(UUID id) {
        domainService.deleteProduct(new ProductId(id));
    }
}