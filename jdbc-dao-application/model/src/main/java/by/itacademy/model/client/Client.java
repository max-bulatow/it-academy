package by.itacademy.model.client;

import by.itacademy.model.BaseModel;
import by.itacademy.model.transport.Transport;
import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "client")
public class Client extends BaseModel {

    @Column(name = "first_name", nullable = false)
    private final String firstName;
    @Column(name = "last_name", nullable = false)
    private final String lastName;
    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private final List<Transport> transportList;

    public Client(
            final Integer id,
            final String firstName,
            final String lastName,
            final List<Transport> transportList
    ) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.transportList = transportList;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public List<Transport> getTransportList() {
        return transportList;
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
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        final Client client = (Client) object;
        return Objects.equals(firstName, client.firstName) && Objects.equals(lastName, client.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName);
    }
}
