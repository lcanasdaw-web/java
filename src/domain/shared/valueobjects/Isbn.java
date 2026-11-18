package domain.shared.valueobjects;

import domain.shared.validation.Check;

public record Isbn(String value) {

    public Isbn {
        Check.notBlank(value);

        value = value.replaceAll("[\\s-]", "");

        if (value.matches("[0-9]{13}")) {
            if (!isValidIsbn13(value)) {
                throw new IllegalArgumentException("El ISBN-13 no es válido");
            }
        } else if (value.matches("[0-9]{9}[0-9X]")) {
            if (!isValidIsbn10(value)) {
                throw new IllegalArgumentException("El ISBN-10 no es válido");
            }
        } else {
            throw new IllegalArgumentException("El ISBN debe tener 10 o 13 caracteres válidos");
        }
    }

    public static Isbn of(String value) {
        return new Isbn(value);
    }

    private static boolean isValidIsbn13(String isbn) {
        if (!isbn.startsWith("978") && !isbn.startsWith("979")) {
            return false;
        }

        int sum = 0;

        for (int i = 0; i < 12; i++) {
            int digit = Character.getNumericValue(isbn.charAt(i));
            sum += digit * (i % 2 == 0 ? 1 : 3);
        }

        int checkDigit = (10 - (sum % 10)) % 10;

        return checkDigit == Character.getNumericValue(isbn.charAt(12));
    }

    private static boolean isValidIsbn10(String isbn) {
        int sum = 0;

        for (int i = 0; i < 10; i++) {
            int digit = isbn.charAt(i) == 'X'
                    ? 10
                    : Character.getNumericValue(isbn.charAt(i));

            sum += digit * (10 - i);
        }

        return sum % 11 == 0;
    }
}