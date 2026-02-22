package lafrindi.stockapp.catalog.infrastructure.persistence.jpa.mapper;

import lafrindi.stockapp.catalog.domain.model.Product;
import lafrindi.stockapp.catalog.domain.valueObject.CategoryId;
import lafrindi.stockapp.catalog.domain.valueObject.Price;
import lafrindi.stockapp.catalog.domain.valueObject.ProductId;
import lafrindi.stockapp.catalog.domain.valueObject.Sku;
import lafrindi.stockapp.catalog.infrastructure.persistence.jpa.entity.JpaProductEntity;

import java.util.Currency;

public class ProductPersistenceMapper {

    public static JpaProductEntity toEntity(Product product) {
        JpaProductEntity entity = new JpaProductEntity();
        entity.setId(product.getProductId().getValue());
        entity.setName(product.getName());
        entity.setSku(product.getSku().getValue());
        entity.setPrice(product.getPrice().getAmount());
        entity.setCurrency(product.getPrice().getCurrency().getCurrencyCode());
        entity.setStockQuantity(product.getStockQuantity());
        entity.setCategoryId(product.getCategoryId().getValue());
        return entity;
    }

    public static Product toDomain(JpaProductEntity entity) {
        return new Product(
                new ProductId(entity.getId()),
                entity.getName(),
                new Sku(entity.getSku()),
                new Price(entity.getPrice(), Currency.getInstance(entity.getCurrency())),
                entity.getStockQuantity(),
                new CategoryId(entity.getCategoryId())
        );
    }
}