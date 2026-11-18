
package domain.shared.valueobjects;

import domain.shared.validation.Check;

public record Isbn(String value) {

    public Isbn {
        Check.notBlank(value);

        value = value.replaceAll("[-\\s]", "").toUpperCase();

        if (!isValid(value)) {
            throw new IllegalArgumentException("El ISBN no es válido");
        }
    }

    public static Isbn of(String value) {
        return new Isbn(value);
    }

    private static boolean isValid(String isbn) {
        if (isbn.matches("[0-9]{13}")) {
            int sum = 0;

            for (int i = 0; i < 12; i++) {
                sum += Character.getNumericValue(isbn.charAt(i)) * (i % 2 == 0 ? 1 : 3);
            }

            int checkDigit = (10 - sum % 10) % 10;
            return checkDigit == Character.getNumericValue(isbn.charAt(12));
        }

        if (isbn.matches("[0-9]{9}[0-9X]")) {
            int sum = 0;

            for (int i = 0; i < 10; i++) {
                int digit = isbn.charAt(i) == 'X'
                        ? 10
                        : Character.getNumericValue(isbn.charAt(i));

                sum += digit * (10 - i);
            }

            return sum % 11 == 0;
        }

        return false;
    }
}
