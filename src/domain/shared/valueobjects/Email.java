package domain.shared.valueobjects;

import domain.shared.validation.Check;

public record Email(String value) {

    public Email {
        Check.notBlank(value);

        value = value.trim();

        if (!value.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
            throw new IllegalArgumentException("El email no tiene un formato válido");
        }
    }

    public static Email of(String value) {
        return new Email(value);
    }
}