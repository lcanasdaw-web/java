package domain.book;

import domain.shared.validation.Check;

public class Publisher {

    private final String name;

    private Publisher(String name) {
        Check.notBlank(name);
        this.name = name.trim();
    }

    public static Publisher of(String name) {
        return new Publisher(name);
    }

    public String getName() {
        return name;
    }
}
