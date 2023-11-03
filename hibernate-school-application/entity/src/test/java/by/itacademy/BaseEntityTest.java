package by.itacademy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BaseEntityTest {

    private final BaseEntity baseEntity = new BaseEntity();
    private final static Integer expectedId = 1;

    @Test
    void testSetAndGetId_happyPath() {
        baseEntity.setId(1);

        final Integer actualId = baseEntity.getId();

        assertEquals(expectedId, actualId);
    }

}