package lafrindi.stockapp.catalog.domain.model;

import lafrindi.stockapp.catalog.domain.valueObject.CategoryId;
import lafrindi.stockapp.catalog.domain.valueObject.Price;
import lafrindi.stockapp.catalog.domain.valueObject.ProductId;
import lafrindi.stockapp.catalog.domain.valueObject.Sku;
import lombok.Getter;
@Getter
public class Product {

    private final ProductId productId;
    private String name;
    private Sku sku;
    private Price price;
    private Integer stockQuantity;
    private CategoryId categoryId;

    public Product(ProductId productId, String name, Sku sku, Price price, Integer stockQuantity, CategoryId categoryId) {
        this.productId = productId;
        this.name = name;
        this.sku = sku;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.categoryId = categoryId;
    }

    public void updatePrice(Price newPrice) {
        this.price = newPrice;
    }

    public void increaseStock(int quantity) {
        if (quantity <= 0) throw new IllegalArgumentException("Invalid quantity");
        this.stockQuantity += quantity;
    }
    public void decreaseStock(int quantity) {
        if (quantity <= 0) throw new IllegalArgumentException("Invalid quantity");
        this.stockQuantity -= quantity;
    }
    public void changeCategory(CategoryId categoryId) {
        this.categoryId = categoryId;
    }
    
    public void updateName(String newName) {
        this.name = newName;
    }
    
    public void updateSku(Sku newSku) {
        this.sku = newSku;
    }
    
    public void updateStockQuantity(Integer newStockQuantity) {
        this.stockQuantity = newStockQuantity;
    }
}