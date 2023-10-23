package by.itacademy.model.client;

import by.itacademy.model.BaseModel;

import java.util.Objects;

public class Client extends BaseModel {

    private final String firstName;
    private final String lastName;

    public Client(final Integer id, final String firstName, final String lastName) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return "Client{" + "id = " + getId() +
                " firstName = '" + firstName + '\'' +
                " lastName = '" + lastName + '\'' +
                '}';
    }

    @Override
    public boolean equals(final Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        final Client client = (Client) object;
        return Objects.equals(firstName, client.firstName) && Objects.equals(lastName, client.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName);
    }
}
