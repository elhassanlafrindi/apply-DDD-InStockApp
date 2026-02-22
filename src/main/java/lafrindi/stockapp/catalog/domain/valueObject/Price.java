package lafrindi.stockapp.catalog.domain.valueObject;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.Currency;
@Getter
@EqualsAndHashCode
public class Price {
    private final BigDecimal amount;
    private final Currency currency;

    public Price(BigDecimal amount, Currency currency) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Price must be positive");
        }
        if (currency == null) {
            throw new IllegalArgumentException("Currency is required");
        }
        this.amount = amount;
        this.currency = currency;
    }
}