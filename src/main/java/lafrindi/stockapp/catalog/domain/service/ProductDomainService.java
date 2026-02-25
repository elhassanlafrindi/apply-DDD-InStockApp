package lafrindi.stockapp.catalog.domain.service;

import lafrindi.stockapp.catalog.domain.model.Product;
import lafrindi.stockapp.catalog.domain.repository.ProductRepository;
import lafrindi.stockapp.catalog.domain.valueObject.CategoryId;
import lafrindi.stockapp.catalog.domain.valueObject.Price;
import lafrindi.stockapp.catalog.domain.valueObject.ProductId;
import lafrindi.stockapp.catalog.domain.valueObject.Sku;
import org.springframework.stereotype.Service;

import java.util.Currency;
import java.util.List;
import java.util.Optional;

@Service
public class ProductDomainService {

    private final ProductRepository productRepository;

    public ProductDomainService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product createProduct(String name, Sku sku, Price price, Integer stock, CategoryId categoryId) {


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

    public Optional<Product> findById(ProductId id) {
        return productRepository.findById(id);
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product updateProduct(ProductId id, String name, Sku sku, Price price, Integer stock, CategoryId categoryId) {
        Optional<Product> existingProduct = productRepository.findById(id);
        if (existingProduct.isEmpty()) {
            throw new IllegalArgumentException("Product not found with id: " + id.getValue());
        }

        Product product = existingProduct.get();
        product.updateName(name);
        product.updateSku(sku);
        product.updatePrice(price);
        product.updateStockQuantity(stock);
        product.changeCategory(categoryId);

        return productRepository.save(product);
    }

    public void deleteProduct(ProductId id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isEmpty()) {
            throw new IllegalArgumentException("Product not found with id: " + id.getValue());
        }
        productRepository.delete(product.get());
    }
}