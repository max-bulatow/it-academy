package by.itacademy.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TransportTest {

    @Test
    void testGetTransportType_happyPath() {
        final var expectedTransportType = TransportType.AUTOMOBILE;
        final var transport = new Transport(expectedTransportType, null);

        final var actualTransportType = transport.getTransportType();

        assertEquals(expectedTransportType, actualTransportType);
    }

    @Test
    void testGetModel_happyPath() {
        final var expectedModel = "Audi Q7";
        final var transport = new Transport(null, "Audi Q7");

        final var actualModel = transport.getModel();

        assertEquals(expectedModel, actualModel);
    }

    @Test
    void testGetPrice_happyPath() {
        final var expectedPrice = 10;
        final var transport = new Transport(TransportType.MOTORCYCLE, "Ninja ZX-14");

        final var actualPrice = transport.getPrice();

        assertEquals(expectedPrice, actualPrice);
    }
}