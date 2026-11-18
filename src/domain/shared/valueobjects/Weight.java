package domain.shared.valueobjects;

import domain.shared.validation.Check;
import java.math.BigDecimal;

public record Weight(BigDecimal value, WeightUnit unit) {

    public Weight {
        Check.positiveDecimal(value);

        if (unit == null) {
            throw new IllegalArgumentException("La unidad de peso no puede ser nula");
        }

        value = value.stripTrailingZeros();
    }

    public static Weight of(BigDecimal value, WeightUnit unit) {
        return new Weight(value, unit);
    }
}