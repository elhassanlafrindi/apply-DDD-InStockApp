package lafrindi.stockapp.inventry.domain.valueObject;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.UUID;

@Getter
@EqualsAndHashCode
public class StockId {
    private final UUID value;

    public StockId(UUID value) {
        if (value == null) {
            throw new IllegalArgumentException("StockId cannot be null");
        }
        this.value = value;
    }

    public static StockId newId() {
        return new StockId(UUID.randomUUID());
    }

    @Override
    public String toString() {
        return value.toString();
    }
}

