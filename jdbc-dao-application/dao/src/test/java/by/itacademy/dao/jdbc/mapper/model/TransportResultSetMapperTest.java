package by.itacademy.dao.jdbc.mapper.model;

import by.itacademy.dao.jdbc.mapper.ResultSetMapperException;
import by.itacademy.model.client.Client;
import by.itacademy.model.transport.ModelType;
import by.itacademy.model.transport.Transport;
import by.itacademy.model.transport.TransportType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TransportResultSetMapperTest {

    @Mock
    private ResultSet rs;

    @Test
    void testMap_happyPath() throws SQLException, ResultSetMapperException {
        final Client client = new Client(1, "Sergey", "Sergeev");
        final Transport expectedTransport = new Transport(1, client, ModelType.TRANSPORTER_T5, TransportType.MINIBUS);
        doReturn (Boolean.TRUE).when(rs).next();
        doReturn(expectedTransport.getId()).when(rs).getInt("id");
        doReturn(expectedTransport.getClient().getId()).when(rs).getInt("client_id");
        doReturn(expectedTransport.getClient().getFirstName()).when(rs).getString("first_name");
        doReturn(expectedTransport.getClient().getLastName()).when(rs).getString("last_name");
        doReturn(expectedTransport.getModelType().getId()).when(rs).getInt("model_type_id");
        doReturn(expectedTransport.getTransportType().getId()).when(rs).getInt("transport_type_id");

        final Transport actualTransport = new TransportResultSetMapper().map(rs);

        assertNotNull(actualTransport);
        assertEquals(expectedTransport.getId(), actualTransport.getId());
        assertEquals(expectedTransport.getClient().getId(), actualTransport.getClient().getId());
        assertEquals(expectedTransport.getClient().getFirstName(), actualTransport.getClient().getFirstName());
        assertEquals(expectedTransport.getClient().getLastName(), actualTransport.getClient().getLastName());
        assertEquals(expectedTransport.getModelType(), actualTransport.getModelType());
        assertEquals(expectedTransport.getTransportType(), actualTransport.getTransportType());

        verify(rs).next();
        verify(rs).getInt("id");
        verify(rs).getInt("client_id");
        verify(rs).getString("first_name");
        verify(rs).getString("last_name");
        verify(rs).getInt("model_type_id");
        verify(rs).getInt("transport_type_id");

        verifyNoMoreInteractions(rs);
    }

    @Test
    void testMap_resultSetMapperException() throws SQLException {
        final Transport expectedTransport = new Transport(1, null, null, null);
        doReturn(Boolean.TRUE).when(rs).next();
        doThrow(SQLException.class).when(rs).getInt("id");

        final Exception errorMessage = assertThrows(ResultSetMapperException.class, () -> {
            final Transport actualTransport = new TransportResultSetMapper().map(rs);
        });

        Assertions.assertEquals(errorMessage.getMessage(), "Failed to map transport");
        verify(rs).next();
        verifyNoMoreInteractions(rs);
    }

}