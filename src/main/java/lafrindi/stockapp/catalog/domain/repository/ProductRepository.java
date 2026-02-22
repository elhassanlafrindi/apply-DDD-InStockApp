package lafrindi.stockapp.catalog.domain.repository;

import lafrindi.stockapp.catalog.domain.model.Product;
import lafrindi.stockapp.catalog.domain.valueObject.ProductId;
import lafrindi.stockapp.catalog.domain.valueObject.Sku;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface ProductRepository {

    Product save(Product product);

    Optional<Product> findById(ProductId productId);

    Optional<Product> findBySku(Sku sku);

    void delete(Product product);
}
