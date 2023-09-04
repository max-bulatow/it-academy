package by.itacademy.transport;

import java.util.Objects;

public class Transport {
    private final TransportType transportType;
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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transport transport = (Transport) o;
        return Objects.equals(transportType, transport.transportType) && Objects.equals(model, transport.model) && Objects.equals(transportType.getPrice(), transport.transportType.getPrice());
    }

}
