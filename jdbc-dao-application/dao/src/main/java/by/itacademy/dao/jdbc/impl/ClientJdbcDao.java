package by.itacademy.dao.jdbc.impl;

import by.itacademy.dao.DaoException;
import by.itacademy.dao.datasource.JdbcDataSourceProperties;
import by.itacademy.dao.jdbc.GenericJdbcDao;
import by.itacademy.dao.jdbc.mapper.ResultSetMapper;
import by.itacademy.model.client.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class ClientJdbcDao extends GenericJdbcDao<Client> {
    public ClientJdbcDao(
            final JdbcDataSourceProperties properties,
            final ResultSetMapper<Client> mapper
    ) throws DaoException {
        super(properties, "client", mapper, null);
    }

    @Override
    protected PreparedStatement getPrepareStatementForCreate(
            final Connection cn,
            final Client client
    ) throws SQLException {
        final PreparedStatement ps = cn.prepareStatement("insert into client (first_name, last_name) values (?, ?)", Statement.RETURN_GENERATED_KEYS);

        ps.setString(1, client.getFirstName());
        ps.setString(2, client.getLastName());

        return ps;
    }

}
