package lafrindi.stockapp.catalog.domain.service;

import lafrindi.stockapp.catalog.domain.model.Product;
import lafrindi.stockapp.catalog.domain.repository.ProductRepository;
import lafrindi.stockapp.catalog.domain.valueObject.CategoryId;
import lafrindi.stockapp.catalog.domain.valueObject.Price;
import lafrindi.stockapp.catalog.domain.valueObject.ProductId;
import lafrindi.stockapp.catalog.domain.valueObject.Sku;

public class ProductDomainService {

    private final ProductRepository productRepository;

    public ProductDomainService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product createProduct(String name, Sku sku, Price price, Integer stock, CategoryId categoryId) {

        // 🔥 règle métier : SKU unique
        productRepository.findBySku(sku).ifPresent(p -> {
            throw new IllegalArgumentException("SKU already exists");
        });

        Product product = new Product(
                ProductId.newId(),
                name,
                sku,
                price,
                stock,
                categoryId
        );

        return productRepository.save(product);
    }
}