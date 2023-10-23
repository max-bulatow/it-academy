package by.itacademy.model.transport;

import by.itacademy.model.IdField;
import by.itacademy.model.NameField;

public enum TransportType implements IdField, NameField {

    AUTOMOBILE(1, "Automobile"),
    MOTORCYCLE(2, "Motorcycle"),
    MINIBUS(3, "Minibus");

    private final Integer id;
    private final String name;

    TransportType(final Integer id, final String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }
}
