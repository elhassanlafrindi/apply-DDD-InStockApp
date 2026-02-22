package lafrindi.stockapp.catalog.domain.valueObject;


import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.UUID;
@Getter
@EqualsAndHashCode
public class CategoryId {

    private final UUID value;

    public CategoryId(UUID value) {
        if (value == null) {
            throw new IllegalArgumentException("CategoryId cannot be null");
        }
        this.value = value;
    }

    public static CategoryId newId() {
        return new CategoryId(UUID.randomUUID());
    }
}