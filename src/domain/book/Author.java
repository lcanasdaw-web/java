package domain.book;

import domain.shared.validation.Check;

public class Author {

    private final String name;

    private Author(String name) {
        Check.notBlank(name);
        this.name = name.trim();
    }

    public static Author of(String name) {
        return new Author(name);
    }

    public String getName() {
        return name;
    }
}
