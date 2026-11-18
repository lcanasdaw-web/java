package domain.client;

import domain.shared.validation.Check;
import domain.shared.valueobjects.Email;
import domain.shared.valueobjects.Phone;

public class Client {

    private final String name;
    private final Email email;
    private final Phone phone;

    private Client(String name, Email email, Phone phone) {
        Check.notBlank(name);

        if (email == null || phone == null) {
            throw new IllegalArgumentException("Email y teléfono son obligatorios");
        }

        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public static Client of(String name, Email email, Phone phone) {
        return new Client(name, email, phone);
    }

    public String getName() {
        return name;
    }

    public Email getEmail() {
        return email;
    }

    public Phone getPhone() {
        return phone;
    }
}
