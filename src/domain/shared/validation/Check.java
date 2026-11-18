package domain.shared.validation;

import java.math.BigDecimal;

public final class Check {

    private Check() {
    }

    public static void notBlank(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("El valor no puede ser nulo ni vacío");
        }
    }

    public static void positive(Integer value) {
        if (value == null || value <= 0) {
            throw new IllegalArgumentException("El valor debe ser un entero positivo");
        }
    }

    public static void notNegative(Integer value) {
        if (value == null || value < 0) {
            throw new IllegalArgumentException("El valor no puede ser negativo ni nulo");
        }
    }

    public static void positiveDecimal(BigDecimal value) {
        if (value == null || value.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El valor decimal debe ser positivo");
        }
    }
}