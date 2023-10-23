package by.itacademy.model;

import by.itacademy.model.annotations.Validation;

import java.util.Objects;


public class Transport {

    private final TransportType transportType;
    @Validation(pattern = "^[a-zA-Z]((\\s|-)?[a-zA-Z0-9])*$")
    private final String model;

    public Transport(final TransportType transportType, final String model) {
        this.transportType = transportType;
        this.model = model;
    }

    public TransportType getTransportType() {
        return transportType;
    }

    public String getModel() {
        return model;
    }

    public Integer getPrice() {
        return transportType.getPrice();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Transport transport = (Transport) o;
        return transportType == transport.transportType && Objects.equals(model, transport.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(transportType, model);
    }

    @Override
    public String toString() {
        return new String(transportType.name() + " " + model + " " + transportType.getPrice().toString());
    }
}