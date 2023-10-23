package it.academy.core.validator.impl;

import by.itacademy.model.Transport;
import by.itacademy.model.TransportType;
import it.academy.core.transport.TransportContainer;
import it.academy.core.validator.FieldValidatorException;
import it.academy.core.validator.Validator;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TransportValidatorTest {

    @Test
    void testValidateTransport_happyPath() throws FieldValidatorException {
        final List<Transport> transportList = new ArrayList<>();
        final List<Transport> validTransportList = new ArrayList<>();
        final List<Transport> invalidTransportList = new ArrayList<>();

        final Validator validator = new TransportValidator();

        final Transport validTransport = new Transport(TransportType.MOTORCYCLE, "Ninja ZX-14");
        final Transport invalidTransport = new Transport(TransportType.AUTOMOBILE, "Audi Q9!№");

        transportList.add(validTransport);
        transportList.add(invalidTransport);

        validTransportList.add(validTransport);
        invalidTransportList.add(invalidTransport);

        final TransportContainer transportContainer = validator.validateTransport(transportList);

        assertNotNull(transportContainer, "transportContainer is null");
        assertEquals(transportContainer.getValidTransport(), validTransportList, "коллекция валидного транспорта пуста");
        assertEquals(transportContainer.getInvalidTransport(), invalidTransportList, "коллекция невалидного транспорта пуста");
    }
}