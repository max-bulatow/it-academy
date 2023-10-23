package by.itacademy.dao.jdbc.impl;

import by.itacademy.dao.Dao;
import by.itacademy.dao.datasource.factory.DataSourcePropertiesFactory;
import by.itacademy.dao.datasource.factory.impl.FileDataSourcePropertiesFactory;
import by.itacademy.dao.datasource.JdbcDataSourceProperties;
import by.itacademy.dao.jdbc.mapper.model.ClientResultSetMapper;
import by.itacademy.model.client.Client;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ClientJdbcDaoIntegrationTest extends BaseJdbcDaoIntegrationTest{

    @Test
    void testCreateClient_happyPath() throws Exception {
        final DataSourcePropertiesFactory factory = new FileDataSourcePropertiesFactory();
        final JdbcDataSourceProperties properties = factory.create("application-test.properties");
        final ClientResultSetMapper mapper = new ClientResultSetMapper();

        final Dao<Client> clientDao = new ClientJdbcDao(properties, mapper);
        final Client client = new Client(null, "Sergey", "Ivanov");

        final Integer id = clientDao.create(client);

        final ResultSetVerifier verifier = (rs) -> {
            final Integer transportId = rs.getInt("id");
            final String firstName = rs.getString("first_name");
            final String lastName = rs.getString("last_name");

            Assertions.assertEquals(id, transportId);
            Assertions.assertEquals(client.getFirstName(), firstName);
            Assertions.assertEquals(client.getLastName(), lastName);
        };

        verifyCreatedRow("client", id, verifier);
    }

}
