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

        if (email == null) {
            throw new IllegalArgumentException("El email no puede ser nulo");
        }

        if (phone == null) {
            throw new IllegalArgumentException("El teléfono no puede ser nulo");
        }

        this.name = name.trim();
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