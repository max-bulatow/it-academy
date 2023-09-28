package by.itacademy;

import by.itacademy.client.Client;
import by.itacademy.model.ModelType;
import by.itacademy.transport.Transport;
import by.itacademy.transport.TransportType;

import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class JdbcApplication {

    public static void main(final String[] args) throws IOException {
        final Properties properties = getProperties("application.properties");

        try (final Connection connection = DriverManager.getConnection(properties.getProperty("url"), properties)) {
            final String selectTransportQuery = "select tr.id, mt.\"name\" as modelName, tt.\"name\" as transportType, cl.first_name as firstName, cl.last_name as lastName from transport tr\n" +
                    "\tleft join model_type mt on tr.model_type_id = mt.id\n" +
                    "\tleft join transport_type tt on tr.transport_type_id = tt.id \n" +
                    "\tleft join client cl on tr.client_id = cl.id;";

            final Statement statement = connection.createStatement();

            final ResultSet transportRS = statement.executeQuery(selectTransportQuery);

            final List<Transport> transportList = getTransportList(transportRS);

            for (Transport transport : transportList) {
                System.out.println(transport);
            }

        } catch (final SQLException exception) {
            exception.printStackTrace();
        }
    }

    private static List<Transport> getTransportList(final ResultSet transportRS) throws SQLException {
        final List<Transport> transportList = new ArrayList<>();

        while (transportRS.next()) {
            final Integer id = transportRS.getInt("id");

            final String modelName = transportRS.getString("modelName");
            final ModelType modelType = new ModelType(modelName);

            final TransportType transportType = TransportType.valueOf(transportRS.getString("transportType"));

            final String firstName = transportRS.getString("firstName");
            final String lastName = transportRS.getString("lastName");
            final Client client = new Client(firstName, lastName);

            final Transport transport = new Transport(id, modelType, transportType, client);
            transportList.add(transport);
        }
        return transportList;
    }

    private static Properties getProperties(final String fileName) throws IOException {
        try (final InputStreamReader reader = new InputStreamReader(JdbcApplication.class.getClassLoader().getResourceAsStream(fileName))) {
            final Properties properties = new Properties();
            properties.load(reader);

            return properties;
        }
    }
}

