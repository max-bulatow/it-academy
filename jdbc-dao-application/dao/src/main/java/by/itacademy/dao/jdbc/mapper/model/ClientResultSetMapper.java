package by.itacademy.dao.jdbc.mapper.model;

import by.itacademy.dao.jdbc.mapper.ResultSetMapper;
import by.itacademy.dao.jdbc.mapper.ResultSetMapperException;
import by.itacademy.model.client.Client;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientResultSetMapper implements ResultSetMapper<Client> {

    @Override
    public Client map(final ResultSet resultSet) throws ResultSetMapperException {
        try {
            if (!resultSet.next()) {
                return null;
            }

            final Integer id = resultSet.getInt("id");
            final String firstName = resultSet.getString("first_name");
            final String lastName = resultSet.getString("last_name");
            return new Client(id, firstName, lastName);
        } catch (final SQLException ex) {
            throw new ResultSetMapperException("Failed to map client", ex);
        }
    }

}
