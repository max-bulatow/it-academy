package by.itacademy.dao.jdbc.provider;

import by.itacademy.dao.Dao;
import by.itacademy.dao.DaoException;
import by.itacademy.dao.datasource.JdbcDataSourceProperties;
import by.itacademy.dao.jdbc.impl.ClientJdbcDao;
import by.itacademy.dao.jdbc.impl.TransportJdbcDao;
import by.itacademy.dao.jdbc.mapper.model.ClientResultSetMapper;
import by.itacademy.dao.jdbc.mapper.model.TransportResultSetMapper;
import by.itacademy.dao.provider.DaoProvider;
import by.itacademy.model.BaseModel;
import by.itacademy.model.client.Client;
import by.itacademy.model.transport.Transport;

import java.util.HashMap;
import java.util.Map;

public class JdbcDaoProvider implements DaoProvider {

    private final Map<Class<? extends BaseModel>, Dao<? extends BaseModel>> map;

    public JdbcDaoProvider(final JdbcDataSourceProperties properties) throws DaoException {
        map = new HashMap<>();

        map.put(Transport.class, new TransportJdbcDao(properties, new TransportResultSetMapper()));
        map.put(Client.class, new ClientJdbcDao(properties, new ClientResultSetMapper()));
    }

    @Override
    public <M extends BaseModel, D extends Dao<M>> D provide(final Class<M> modelClass) {
        return (D) map.get(modelClass);
    }
}
