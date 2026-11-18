
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
        testBook();

        System.out.println("Todas las pruebas han finalizado correctamente");
    }

    private static void testEmail() {
        Email email = Email.of("juan@gmail.com");
        assertTrue(email.value().equals("juan@gmail.com"));

        assertThrows(() -> Email.of("email-invalido"));
        assertThrows(() -> Email.of(""));
        assertThrows(() -> Email.of(null));
    }

    private static void testPhone() {
        Phone phone = Phone.of("+34600123456");
        assertTrue(phone.value().equals("+34600123456"));

        assertThrows(() -> Phone.of("123"));
        assertThrows(() -> Phone.of(""));
        assertThrows(() -> Phone.of(null));
    }

    private static void testIsbn() {
        Isbn isbn = Isbn.of("9780306406157");
        assertTrue(isbn.value().equals("9780306406157"));

        assertThrows(() -> Isbn.of("123456"));
        assertThrows(() -> Isbn.of(null));
    }

    private static void testMoney() {
        Money money = Money.of(
                new BigDecimal("25.50"),
                Currency.getInstance("EUR")
        );

        assertTrue(money.amount().compareTo(new BigDecimal("25.50")) == 0);

        assertThrows(() -> Money.of(
                BigDecimal.ZERO,
                Currency.getInstance("EUR")
        ));

        assertThrows(() -> Money.of(
                new BigDecimal("-5"),
                Currency.getInstance("EUR")
        ));
    }

    private static void testWeight() {
        Weight weight = Weight.of(
                new BigDecimal("1.5"),
                WeightUnit.KILOGRAM
        );

        assertTrue(weight.value().compareTo(new BigDecimal("1.5")) == 0);

        assertThrows(() -> Weight.of(
                BigDecimal.ZERO,
                WeightUnit.GRAM
        ));
    }

    private static void testClient() {
        Client client = Client.of(
                "Juan",
                Email.of("juan@gmail.com"),
                Phone.of("600123456")
        );

        assertTrue(client.getName().equals("Juan"));

        assertThrows(() -> Client.of(
                "",
                Email.of("juan@gmail.com"),
                Phone.of("600123456")
        ));
    }

    private static void testBook() {
        Publisher publisher = Publisher.of("Editorial DAW");
        Author author = Author.of("Juan");

        Book book = Book.of(
                "Java desde cero",
                Isbn.of("9780306406157"),
                Money.of("25.50", "EUR"),
                Weight.of(new BigDecimal("1.2"), WeightUnit.KILOGRAM),
                publisher,
                author
        );

        assertTrue(book.getTitle().equals("Java desde cero"));

        assertThrows(() -> Publisher.of(""));
        assertThrows(() -> Author.of(null));
    }

    private static void assertTrue(boolean condition) {
        if (!condition) {
            throw new AssertionError("La prueba ha fallado");
        }
    }

    private static void assertThrows(Runnable action) {
        try {
            action.run();
            throw new AssertionError("Se esperaba IllegalArgumentException");
        } catch (IllegalArgumentException expected) {
        }
    }
}
