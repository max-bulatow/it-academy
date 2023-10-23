package by.itacademy.model.client;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ClientTest {

    final static Client client = new Client(1, "Andrey", "Andreev");

    @Test
    void testGetFirstName_happyPath() {
        final String expectedFirstName = "Andrey";

        final String actualFirstName = client.getFirstName();

        assertEquals(expectedFirstName, actualFirstName);
    }

    @Test
    void testGetLastName_happyPath() {
        final String expectedLastName = "Andreev";

        final String actualLastName = client.getLastName();

        assertEquals(expectedLastName, actualLastName);
    }

    @Test
    void testGetId_happyPath() {
        final Integer expectedId = 1;

        final Integer actualId = client.getId();

        assertEquals(expectedId, actualId);
    }
}