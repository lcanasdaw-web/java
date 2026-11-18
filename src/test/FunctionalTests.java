package test;

import domain.client.Client;
import domain.book.Author;
import domain.book.Book;
import domain.book.Publisher;
import domain.shared.valueobjects.Email;
import domain.shared.valueobjects.Isbn;
import domain.shared.valueobjects.Money;
import domain.shared.valueobjects.Phone;
import domain.shared.valueobjects.Weight;
import domain.shared.valueobjects.WeightUnit;

import java.math.BigDecimal;
import java.util.Currency;

public class FunctionalTests {

    public static void main(String[] args) {
        testEmail();
        testPhone();
        testIsbn();
        testMoney();
        testWeight();
        testClient();
        testAuthor();
        testPublisher();
        testBook();

        System.out.println("Todas las pruebas han finalizado correctamente");
    }

    private static void testEmail() {
        Email email = Email.of("juan@gmail.com");
        assertTrue(email.value().equals("juan@gmail.com"));

        assertThrows(() -> Email.of(""));
        assertThrows(() -> Email.of(null));
        assertThrows(() -> Email.of("email-invalido"));
        assertThrows(() -> Email.of("juan @gmail.com"));
        assertThrows(() -> Email.of("juan@gmail"));
    }

    private static void testPhone() {
        Phone phone = Phone.of("+34600123456");
        assertTrue(phone.value().equals("+34600123456"));

        Phone normalized = Phone.of("+34 600 123 456");
        assertTrue(normalized.value().equals("+34600123456"));

        assertThrows(() -> Phone.of("123"));
        assertThrows(() -> Phone.of(""));
        assertThrows(() -> Phone.of(null));
        assertThrows(() -> Phone.of("abcdefghij"));
        assertThrows(() -> Phone.of("+1234567890123456"));
    }

    private static void testIsbn() {
        Isbn isbn13 = Isbn.of("9780306406157");
        assertTrue(isbn13.value().equals("9780306406157"));

        Isbn isbn10 = Isbn.of("0306406152");
        assertTrue(isbn10.value().equals("0306406152"));

        assertThrows(() -> Isbn.of("123456"));
        assertThrows(() -> Isbn.of(null));
        assertThrows(() -> Isbn.of(""));
        assertThrows(() -> Isbn.of("9780306406158"));
        assertThrows(() -> Isbn.of("0306406153"));
    }

    private static void testMoney() {
        Money money = Money.of(
                new BigDecimal("25.50"),
                Currency.getInstance("EUR")
        );

        assertTrue(money.amount().compareTo(new BigDecimal("25.50")) == 0);
        assertTrue(money.currency().equals(Currency.getInstance("EUR")));

        assertThrows(() -> Money.of(
                BigDecimal.ZERO,
                Currency.getInstance("EUR")
        ));

        assertThrows(() -> Money.of(
                new BigDecimal("-5"),
                Currency.getInstance("EUR")
        ));

        assertThrows(() -> Money.of(
                null,
                Currency.getInstance("EUR")
        ));

        assertThrows(() -> Money.of(
                new BigDecimal("10"),
                null
        ));

        assertThrows(() -> Money.of("abc", "EUR"));
        assertThrows(() -> Money.of("10", "INVALID"));
    }

    private static void testWeight() {
        Weight weight = Weight.of(
                new BigDecimal("1.5"),
                WeightUnit.KILOGRAM
        );

        assertTrue(weight.value().compareTo(new BigDecimal("1.5")) == 0);
        assertTrue(weight.unit() == WeightUnit.KILOGRAM);

        assertThrows(() -> Weight.of(
                BigDecimal.ZERO,
                WeightUnit.GRAM
        ));

        assertThrows(() -> Weight.of(
                new BigDecimal("-1"),
                WeightUnit.GRAM
        ));

        assertThrows(() -> Weight.of(null, WeightUnit.GRAM));
        assertThrows(() -> Weight.of(new BigDecimal("1"), null));
    }

    private static void testClient() {
        Email email = Email.of("juan@gmail.com");
        Phone phone = Phone.of("600123456");

        Client client = Client.of("Juan", email, phone);

        assertTrue(client.getName().equals("Juan"));
        assertTrue(client.getEmail().equals(email));
        assertTrue(client.getPhone().equals(phone));

        assertThrows(() -> Client.of("", email, phone));
        assertThrows(() -> Client.of(null, email, phone));
        assertThrows(() -> Client.of("Juan", null, phone));
        assertThrows(() -> Client.of("Juan", email, null));
    }

    private static void testAuthor() {
        Author author = Author.of("Juan");
        assertTrue(author.getName().equals("Juan"));

        assertThrows(() -> Author.of(""));
        assertThrows(() -> Author.of(null));
        assertThrows(() -> Author.of("   "));
    }

    private static void testPublisher() {
        Publisher publisher = Publisher.of("Editorial DAW");
        assertTrue(publisher.getName().equals("Editorial DAW"));

        assertThrows(() -> Publisher.of(""));
        assertThrows(() -> Publisher.of(null));
        assertThrows(() -> Publisher.of("   "));
    }

    private static void testBook() {
        Publisher publisher = Publisher.of("Editorial DAW");
        Author author = Author.of("Juan");

        Isbn isbn = Isbn.of("9780306406157");
        Money price = Money.of("25.50", "EUR");
        Weight weight = Weight.of(
                new BigDecimal("1.2"),
                WeightUnit.KILOGRAM
        );

        Book book = Book.of(
                "Java desde cero",
                isbn,
                price,
                weight,
                publisher,
                author
        );

        assertTrue(book.getTitle().equals("Java desde cero"));
        assertTrue(book.getIsbn().equals(isbn));
        assertTrue(book.getPrice().equals(price));
        assertTrue(book.getWeight().equals(weight));
        assertTrue(book.getPublisher().equals(publisher));
        assertTrue(book.getAuthor().equals(author));

        assertThrows(() -> Book.of("", isbn, price, weight, publisher, author));
        assertThrows(() -> Book.of(null, isbn, price, weight, publisher, author));
        assertThrows(() -> Book.of("Libro", null, price, weight, publisher, author));
        assertThrows(() -> Book.of("Libro", isbn, null, weight, publisher, author));
        assertThrows(() -> Book.of("Libro", isbn, price, null, publisher, author));
        assertThrows(() -> Book.of("Libro", isbn, price, weight, null, author));
        assertThrows(() -> Book.of("Libro", isbn, price, weight, publisher, null));
    }

    private static void assertTrue(boolean condition) {
        if (!condition) {
            throw new AssertionError("La prueba ha fallado");
        }
    }

    private static void assertThrows(Runnable action) {
        try {
            action.run();
        } catch (IllegalArgumentException expected) {
            return;
        }

        throw new AssertionError("Se esperaba IllegalArgumentException");
    }
}