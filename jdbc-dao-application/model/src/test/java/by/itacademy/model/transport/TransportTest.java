package by.itacademy.model.transport;

import by.itacademy.model.client.Client;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TransportTest {

    final static Client client = new Client(1, "Andrey", "Andreev");
    final static Transport transport = new Transport(1, client, ModelType.AUDI_Q7, TransportType.AUTOMOBILE);

    @Test
    void testGetId_happyPath() {
        final Integer expectedId = 1;

        final Integer actualId = transport.getId();

        assertEquals(expectedId, actualId);
    }

    @Test
    void testGetClient_happyPath() {
        final Client expectedClient = new Client(1, "Andrey", "Andreev");

        final Client actualClient = transport.getClient();

        assertEquals(expectedClient, actualClient);
    }

    @Test
    void testGetModelType_happyPath() {
        final ModelType expectedModelType = ModelType.AUDI_Q7;

        final ModelType actualModelType = transport.getModelType();

        assertEquals(expectedModelType, actualModelType);
    }

    @Test
    void getTransportType() {
        final TransportType expectedTransportType = TransportType.AUTOMOBILE;

        final TransportType actualTransportType = transport.getTransportType();

        assertEquals(expectedTransportType, actualTransportType);
    }
}