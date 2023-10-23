package by.itacademy.model.transport;

import by.itacademy.model.BaseModel;
import by.itacademy.model.client.Client;

public class Transport extends BaseModel {

    private final Client client;
    private final ModelType modelType;
    private final TransportType transportType;

    public Transport(
            final Integer id,
            final Client client,
            final ModelType modelType,
            final TransportType transportType
    ) {
        super(id);
        this.client = client;
        this.modelType = modelType;
        this.transportType = transportType;
    }

    public Client getClient() {
        return client;
    }

    public ModelType getModelType() {
        return modelType;
    }

    public TransportType getTransportType() {
        return transportType;
    }

    @Override
    public String toString() {
        return "Transport{" + "id = " + getId() +
                " " + client +
                ", modelType = " + modelType +
                ", transportType = " + transportType +
                '}';
    }
}
