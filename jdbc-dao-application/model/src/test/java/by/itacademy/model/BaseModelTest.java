package by.itacademy.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BaseModelTest {

    @Test
    void testGetId_happyPath() {
        final Integer expectedId = 1;
        final BaseModel baseModel = new BaseModel(1);

        final Integer actualId = baseModel.getId();

        assertEquals(expectedId, actualId);
    }
}