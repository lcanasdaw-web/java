package domain.shared.validation;

public final class Check {

    private Check() {
    }

    public static void notBlank(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("El valor no puede ser nulo o vacío");
        }
    }

    public static void positive(Integer value) {
        if (value == null || value <= 0) {
            throw new IllegalArgumentException("El valor debe ser positivo");
        }
    }

    public static void notNegative(Integer value) {
        if (value == null || value < 0) {
            throw new IllegalArgumentException("El valor no puede ser nulo ni negativo");
        }
    }

    public static void positiveDecimal(java.math.BigDecimal value) {
        if (value == null || value.compareTo(java.math.BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El valor debe ser positivo");
        }
    }
}
