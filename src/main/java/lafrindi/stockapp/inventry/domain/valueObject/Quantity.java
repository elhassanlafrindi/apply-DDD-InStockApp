package lafrindi.stockapp.inventry.domain.valueObject;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class Quantity {
    private final Integer value;

    public Quantity(Integer value) {
        if (value == null || value < 0) {
            throw new IllegalArgumentException("Quantity must be non-negative");
        }
        this.value = value;
    }

    public static Quantity of(Integer value) {
        return new Quantity(value);
    }

    public Quantity add(Quantity other) {
        return new Quantity(this.value + other.value);
    }

    public Quantity subtract(Quantity other) {
        int result = this.value - other.value;
        if (result < 0) {
            throw new IllegalArgumentException("Resulting quantity cannot be negative");
        }
        return new Quantity(result);
    }

    public boolean isGreaterThan(Quantity other) {
        return this.value > other.value;
    }

    public boolean isGreaterThanOrEqual(Quantity other) {
        return this.value >= other.value;
    }

    public boolean isLessThan(Quantity other) {
        return this.value < other.value;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}

