package domain.shared.valueobjects;

import domain.shared.validation.Check;

public record Email(String value) {

    public Email {
        Check.notBlank(value);

        if (!value.matches("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$")) {
            throw new IllegalArgumentException("El email no tiene un formato válido");
        }
    }

    public static Email of(String value) {
        return new Email(value);
    }
}
