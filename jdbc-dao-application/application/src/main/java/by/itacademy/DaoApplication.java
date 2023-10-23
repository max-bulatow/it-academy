package by.itacademy;

import by.itacademy.dao.Dao;
import by.itacademy.dao.datasource.JdbcDataSourceProperties;
import by.itacademy.dao.datasource.factory.DataSourcePropertiesFactory;
import by.itacademy.dao.datasource.factory.impl.FileDataSourcePropertiesFactory;
import by.itacademy.dao.jdbc.provider.JdbcDaoProvider;
import by.itacademy.dao.provider.DaoProvider;
import by.itacademy.model.client.Client;
import by.itacademy.model.transport.ModelType;
import by.itacademy.model.transport.Transport;
import by.itacademy.model.transport.TransportType;

public class DaoApplication {

    public static void main(final String[] args) throws Exception {
        final DataSourcePropertiesFactory dataSourcePropertiesFactory = new FileDataSourcePropertiesFactory();
        final JdbcDataSourceProperties properties = dataSourcePropertiesFactory.create("application.properties");

        final DaoProvider provider = new JdbcDaoProvider(properties);
        final Dao<Client> clientDao = provider.provide(Client.class);

        final Client clientCreate = new Client(6, "Sergey", "Sergeev");
        clientDao.create(clientCreate);
        System.out.println("Клиент добавленный в BD: " + clientCreate);

        final Client clientRead = clientDao.read(2);
        System.out.println("Прочитанный клиент из BD: " + clientRead.toString());

        final Dao<Transport> transportDao = provider.provide(Transport.class);
        final Transport transportCreate = new Transport(null, clientCreate, ModelType.NINJA_ZX14, TransportType.MOTORCYCLE);
        transportDao.create(transportCreate);
        System.out.println("Транспорт добавленный в BD: " + transportCreate);

        final Transport transportRead = transportDao.readWithLeftJoin(2, "client");
        System.out.println("Прочитанный транспорт из BD: " + transportRead.toString());

    }
}
