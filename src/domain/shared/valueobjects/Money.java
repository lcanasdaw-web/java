package domain.shared.valueobjects;

import domain.shared.validation.Check;
import java.math.BigDecimal;
import java.util.Currency;

public record Money(BigDecimal amount, Currency currency) {

    public Money {
        Check.positiveDecimal(amount);

        if (currency == null) {
            throw new IllegalArgumentException("La moneda no puede ser nula");
        }

        amount = amount.stripTrailingZeros();
    }

    public static Money of(BigDecimal amount, Currency currency) {
        return new Money(amount, currency);
    }

    public static Money of(String amount, String currencyCode) {
        Check.notBlank(amount);
        Check.notBlank(currencyCode);

        return new Money(
                new BigDecimal(amount),
                Currency.getInstance(currencyCode));
    }
}