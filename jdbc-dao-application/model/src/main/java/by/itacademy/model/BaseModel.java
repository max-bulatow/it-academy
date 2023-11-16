package by.itacademy.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public class BaseModel implements IdField {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Integer id;

    public BaseModel(final Integer id) {
        this.id = id;
    }

    @Override
    public Integer getId() {
        return id;
    }
}
