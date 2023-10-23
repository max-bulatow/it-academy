package by.itacademy.model.transport;

import by.itacademy.model.IdField;
import by.itacademy.model.NameField;

public enum ModelType implements IdField, NameField {

    TRANSPORTER_T5(1, "TRANSPORTER T5"),
    SPRINTER_264(2, "SPRINTER 264"),
    NINJA_ZX14(3, "NINJA ZX14"),
    MAZDA_CX7(4, "MAZDA CX7"),
    BMW_M5(5, "BMW M5"),
    AUDI_Q7(6, "AUDI Q7");

    private final Integer id;
    private final String name;

    ModelType(final Integer id, final String name) {
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
