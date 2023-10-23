package by.itacademy.dao.jdbc.impl;

import by.itacademy.dao.Dao;
import by.itacademy.dao.datasource.JdbcDataSourceProperties;
import by.itacademy.dao.datasource.factory.DataSourcePropertiesFactory;
import by.itacademy.dao.datasource.factory.impl.FileDataSourcePropertiesFactory;
import by.itacademy.dao.jdbc.mapper.model.TransportResultSetMapper;
import by.itacademy.model.client.Client;
import by.itacademy.model.transport.ModelType;
import by.itacademy.model.transport.Transport;
import by.itacademy.model.transport.TransportType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TransportJdbcDaoIntegrationTest extends BaseJdbcDaoIntegrationTest {

    @Test
    void testCreateTransport_happyPath() throws Exception {
        final DataSourcePropertiesFactory factory = new FileDataSourcePropertiesFactory();
        final JdbcDataSourceProperties properties = factory.create("application-test.properties");
        final TransportResultSetMapper mapper = new TransportResultSetMapper();

        final Client expectedClient = new Client(1, "Ivan", "Ivanov");
        final ModelType expectedModelType = ModelType.TRANSPORTER_T5;
        final TransportType expectedTransportType = TransportType.AUTOMOBILE;

        final Dao<Transport> transportDao = new TransportJdbcDao(properties, mapper);
        final Transport transport = new Transport(1, expectedClient, expectedModelType, expectedTransportType);

        final Integer id = transportDao.create(transport);

        final ResultSetVerifier verifier = (rs) -> {
            final Integer transportId = rs.getInt("id");
            final Integer clientId = rs.getInt("client_id");
            final Integer modelTypeId = rs.getInt("model_type_id");
            final Integer transportTypeId = rs.getInt("transport_type_id");

            Assertions.assertEquals(id, transportId);
            Assertions.assertEquals(expectedClient.getId(), clientId);
            Assertions.assertEquals(expectedModelType.getId(), modelTypeId);
            Assertions.assertEquals(expectedTransportType.getId(), transportTypeId);
        };

        verifyCreatedRow("transport", id, verifier);
    }
}
