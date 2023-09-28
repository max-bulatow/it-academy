package by.itacademy.transport;

import by.itacademy.client.Client;
import by.itacademy.model.ModelType;

public class Transport {
    private final Integer id;
    private final ModelType modelType;
    private final TransportType transportType;
    private final Client client;

    public Transport(final Integer id, final ModelType modelType, final TransportType transportType, final Client client) {
        this.id = id;
        this.modelType = modelType;
        this.transportType = transportType;
        this.client = client;
    }

    @Override
    public String toString() {
        return "Id = " + id + " | "
                + "Model name = " + modelType.getModelName() + " | "
                + "Transport Type = " + transportType.name() + " | "
                + "Client first name = " + client.getFirstName() + " | "
                + "Client second name = " + client.getLastName();
    }
}
