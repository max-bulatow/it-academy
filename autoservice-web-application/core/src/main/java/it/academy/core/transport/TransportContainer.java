package it.academy.core.transport;

import by.itacademy.model.Transport;

import java.util.List;

public class TransportContainer {
    private final List<Transport> validTransport;
    private final List<Transport> invalidTransport;

    public TransportContainer(final List<Transport> validTransport, final List<Transport> invalidTransport) {
        this.validTransport = validTransport;
        this.invalidTransport = invalidTransport;
    }

    public List<Transport> getValidTransport() {
        return validTransport;
    }

    public List<Transport> getInvalidTransport() {
        return invalidTransport;
    }
}
