package domain.book;

import domain.shared.validation.Check;
import domain.shared.valueobjects.Isbn;
import domain.shared.valueobjects.Money;
import domain.shared.valueobjects.Weight;

public class Book {

    private final String title;
    private final Isbn isbn;
    private final Money price;
    private final Weight weight;
    private final Publisher publisher;
    private final Author author;

    private Book(
            String title,
            Isbn isbn,
            Money price,
            Weight weight,
            Publisher publisher,
            Author author
    ) {
        Check.notBlank(title);

        if (isbn == null) {
            throw new IllegalArgumentException("El ISBN no puede ser nulo");
        }

        if (price == null) {
            throw new IllegalArgumentException("El precio no puede ser nulo");
        }

        if (weight == null) {
            throw new IllegalArgumentException("El peso no puede ser nulo");
        }

        if (publisher == null) {
            throw new IllegalArgumentException("La editorial no puede ser nula");
        }

        if (author == null) {
            throw new IllegalArgumentException("El autor no puede ser nulo");
        }

        this.title = title.trim();
        this.isbn = isbn;
        this.price = price;
        this.weight = weight;
        this.publisher = publisher;
        this.author = author;
    }

    public static Book of(
            String title,
            Isbn isbn,
            Money price,
            Weight weight,
            Publisher publisher,
            Author author
    ) {
        return new Book(title, isbn, price, weight, publisher, author);
    }

    public String getTitle() {
        return title;
    }

    public Isbn getIsbn() {
        return isbn;
    }

    public Money getPrice() {
        return price;
    }

    public Weight getWeight() {
        return weight;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public Author getAuthor() {
        return author;
    }
}
