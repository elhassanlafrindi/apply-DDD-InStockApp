package lafrindi.stockapp.catalog.application.dto;

import java.math.BigDecimal;
import java.util.UUID;

public class ProductRequest {
    public String name;
    public String sku;
    public BigDecimal price;
    public String currency;
    public Integer stockQuantity;
    public UUID categoryId;
}