package by.itacademy.dao.jdbc.mapper.model;

import by.itacademy.dao.jdbc.mapper.ResultSetMapper;
import by.itacademy.dao.jdbc.mapper.ResultSetMapperException;
import by.itacademy.model.IdField;
import by.itacademy.model.client.Client;
import by.itacademy.model.transport.ModelType;
import by.itacademy.model.transport.Transport;
import by.itacademy.model.transport.TransportType;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TransportResultSetMapper implements ResultSetMapper<Transport> {

    @Override
    public Transport map(final ResultSet resultSet) throws ResultSetMapperException {
        try {
            if (!resultSet.next()) {
                return null;
            }

            final Integer id = resultSet.getInt("id");

            final Integer clientId = resultSet.getInt("client_id");
            final String clientFirstName = resultSet.getString("first_name");
            final String clientLastName = resultSet.getString("last_name");
            final Client client = new Client(clientId, clientFirstName, clientLastName);

            final Integer modelTypeId = resultSet.getInt("model_type_id");
            final ModelType modelType = mapToEnum(ModelType.class, modelTypeId);

            final Integer transportTypeId = resultSet.getInt("transport_type_id");
            final TransportType transportType = mapToEnum(TransportType.class, transportTypeId);

            return new Transport(id, client, modelType, transportType);
        } catch (final SQLException ex) {
            throw new ResultSetMapperException("Failed to map transport", ex);
        }
    }

    private static <T extends Enum<T> & IdField> T mapToEnum(final Class<T> enumClass, final Integer id) {
        for (final T value : enumClass.getEnumConstants()) {
            if (value.getId().equals(id)) {
                return value;
            }
        }

        return null;
    }

}
