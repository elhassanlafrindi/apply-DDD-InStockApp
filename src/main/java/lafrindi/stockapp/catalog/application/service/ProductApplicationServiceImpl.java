package lafrindi.stockapp.catalog.application.service;

import lafrindi.stockapp.catalog.application.dto.ProductRequest;
import lafrindi.stockapp.catalog.application.dto.ProductResponse;
import lafrindi.stockapp.catalog.application.mapper.ProductMapper;
import lafrindi.stockapp.catalog.domain.model.Product;
import lafrindi.stockapp.catalog.domain.service.ProductDomainService;
import lafrindi.stockapp.catalog.domain.valueObject.CategoryId;
import lafrindi.stockapp.catalog.domain.valueObject.Price;
import lafrindi.stockapp.catalog.domain.valueObject.Sku;
import org.springframework.stereotype.Service;

import java.util.Currency;
@Service
public class ProductApplicationServiceImpl implements ProductApplicationService {

    private final ProductDomainService domainService;

    public ProductApplicationServiceImpl(ProductDomainService domainService) {
        this.domainService = domainService;
    }

    @Override
    public ProductResponse create(ProductRequest request) {

        Product product = domainService.createProduct(
                request.name,
                new Sku(request.sku),
                new Price(request.price, Currency.getInstance(request.currency)),
                request.stockQuantity,
                new CategoryId(request.categoryId)
        );

        return ProductMapper.toResponse(product);
    }
}