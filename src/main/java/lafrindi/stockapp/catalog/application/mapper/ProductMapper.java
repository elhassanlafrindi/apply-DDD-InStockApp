package lafrindi.stockapp.catalog.application.mapper;

import lafrindi.stockapp.catalog.application.dto.ProductRequest;
import lafrindi.stockapp.catalog.application.dto.ProductResponse;
import lafrindi.stockapp.catalog.domain.model.Product;
import lafrindi.stockapp.catalog.domain.valueObject.CategoryId;
import lafrindi.stockapp.catalog.domain.valueObject.Price;
import lafrindi.stockapp.catalog.domain.valueObject.ProductId;
import lafrindi.stockapp.catalog.domain.valueObject.Sku;

import java.util.Currency;

public class ProductMapper {

    public static Product toDomain(ProductRequest request) {
        return new Product(
                ProductId.newId(),
                request.name,
                new Sku(request.sku),
                new Price(request.price, Currency.getInstance(request.currency)),
                request.stockQuantity,
                new CategoryId(request.categoryId)
        );
    }

    public static ProductResponse toResponse(Product product) {
        ProductResponse res = new ProductResponse();
        res.productId = product.getProductId().getValue();
        res.name = product.getName();
        res.sku = product.getSku().getValue();
        res.price = product.getPrice().getAmount();
        res.currency = product.getPrice().getCurrency().getCurrencyCode();
        res.stockQuantity = product.getStockQuantity();
        res.categoryId = product.getCategoryId().getValue();
        return res;
    }
}
