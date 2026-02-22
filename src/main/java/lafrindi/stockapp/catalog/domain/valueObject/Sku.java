package lafrindi.stockapp.catalog.domain.valueObject;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class Sku {
    private final String value;

    public Sku(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("SKU cannot be empty");
        }
        this.value = value;
    }
}