package by.itacademy.dao.jdbc.impl;

import by.itacademy.dao.DaoException;
import by.itacademy.dao.datasource.JdbcDataSourceProperties;
import by.itacademy.dao.jdbc.GenericJdbcDao;
import by.itacademy.dao.jdbc.mapper.ResultSetMapper;
import by.itacademy.model.transport.Transport;

import java.sql.*;

public class TransportJdbcDao extends GenericJdbcDao<Transport> {

    public TransportJdbcDao(
            final JdbcDataSourceProperties properties,
            final ResultSetMapper<Transport> mapper
    ) throws DaoException {
        super(properties, "transport", mapper, null);
    }

    @Override
    protected PreparedStatement getPrepareStatementForCreate(
            final Connection cn,
            final Transport transport
    ) throws SQLException {
        final PreparedStatement ps = cn.prepareStatement("insert into transport (client_id, model_type_id, transport_type_id) values (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

        if (transport.getClient() != null) {
            ps.setInt(1, transport.getClient().getId());
        } else {
            ps.setNull(1, Types.INTEGER);
        }

        ps.setInt(2, transport.getModelType().getId());
        ps.setInt(3, transport.getTransportType().getId());

        return ps;
    }

}
