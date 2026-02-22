package lafrindi.stockapp.catalog.infrastructure.persistence.jpa.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "products")
@Data
public class JpaProductEntity {

    @Id
    private UUID id;

    private String name;
    private String sku;
    private BigDecimal price;
    private String currency;
    private Integer stockQuantity;
    private UUID categoryId;
}