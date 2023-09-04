package by.itacademy.validator.impl;

import by.itacademy.transport.Transport;
import by.itacademy.transport.TransportType;
import by.itacademy.validator.validator;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class TransportValidatorTest {

    @Test
    void transportValidator_happyPath() {
        final List<Transport> transportList = new ArrayList<>();
        final List<Transport> validTransportList = new ArrayList<>();
        final List<Transport> invalidTransportList = new ArrayList<>();

        final validator validator = new TransportValidator();

        final Transport validTransport = new Transport(TransportType.MOTORCYCLE, "Ninja ZX-14");
        final Transport invalidTransport = new Transport(TransportType.AUTOMOBILE, "Audi Q9!№");

        transportList.add(validTransport);
        transportList.add(invalidTransport);

        validTransportList.add(validTransport);
        invalidTransportList.add(invalidTransport);

        final var mapTransport = validator.mapTransportList("valid", "invalid", transportList);

        assertNotNull(mapTransport, "mapTransport is null");
        assertEquals(mapTransport.get("valid"), validTransportList, "коллекция валидного транспорта пуста");
        assertEquals(mapTransport.get("invalid"), invalidTransportList, "коллекция невалидного транспорта пуста");

    }
}