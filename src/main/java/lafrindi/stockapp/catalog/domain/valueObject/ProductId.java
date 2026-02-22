package lafrindi.stockapp.catalog.domain.valueObject;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.UUID;
@Getter
@EqualsAndHashCode
public class ProductId {
    private final UUID value;

    public ProductId(UUID value) {
        if (value == null) {
            throw new IllegalArgumentException("ProductId cannot be null");
        }
        this.value = value;
    }

    public static ProductId newId() {
        return new ProductId(UUID.randomUUID());
    }
}
