package by.itacademy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BasePersonEntityTest {

    private final BasePersonEntity basePersonEntity = new BasePersonEntity();
    private final static String expectedFirstName = "Ivan";
    private final static String expectedLastName = "Ivanov";

    @Test
    void testSetAndGetFirstName_happyPath() {
        basePersonEntity.setFirstName("Ivan");

        final String actualFirstName = basePersonEntity.getFirstName();

        assertEquals(expectedFirstName, actualFirstName);
    }

    @Test
    void testSetAndGetLastName_happyPath() {
        basePersonEntity.setLastName("Ivanov");

        final String actualLastName = basePersonEntity.getLastName();

        assertEquals(expectedLastName, actualLastName);
    }

}