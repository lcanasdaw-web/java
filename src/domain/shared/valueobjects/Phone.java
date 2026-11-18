package domain.shared.valueobjects;

import domain.shared.validation.Check;

public record Phone(String value) {

    public Phone {
        Check.notBlank(value);

        value = value.replaceAll("[\\s()-]", "");

        if (!value.matches("^\\+?[0-9]{9,15}$")) {
            throw new IllegalArgumentException("El teléfono no tiene un formato válido");
        }
    }

    public static Phone of(String value) {
        return new Phone(value);
    }
}