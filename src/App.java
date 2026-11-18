import domain.book.Author;
import domain.book.Book;
import domain.book.Publisher;
import domain.client.Client;
import domain.shared.valueobjects.Email;
import domain.shared.valueobjects.Phone;
import domain.shared.valueobjects.Isbn;
import domain.shared.valueobjects.Money;
import domain.shared.valueobjects.Weight;
import domain.shared.valueobjects.WeightUnit;

import java.math.BigDecimal;
import java.util.Currency;

public class App {
    public static void main(String[] args) {

        Author author = Author.of("J.R.R. Tolkien");
        Publisher publisher = Publisher.of("Minotauro");

        Book book = Book.of(
            "El Señor de los Anillos",
            Isbn.of("9788445000663"),
            Money.of(new BigDecimal("29.99"), Currency.getInstance("EUR")),
            Weight.of(new BigDecimal("0.850"), WeightUnit.KILOGRAM),
            publisher,
            author
        );

        Client client = Client.of(
            "Carlos García",
            Email.of("carlos@example.com"),
            Phone.of("+34612345678")
        );

        System.out.println("=== DELIVERYBOOKS ===");
        System.out.println("Cliente: " + client.getName());
        System.out.println("Libro: " + book.getTitle());
        System.out.println("Autor: " + author.getName());
        System.out.println("Editorial: " + publisher.getName());
    }
}