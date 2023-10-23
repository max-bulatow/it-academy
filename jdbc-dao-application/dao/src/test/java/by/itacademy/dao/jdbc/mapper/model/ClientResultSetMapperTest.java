package by.itacademy.dao.jdbc.mapper.model;

import by.itacademy.dao.jdbc.mapper.ResultSetMapperException;
import by.itacademy.model.client.Client;
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
class ClientResultSetMapperTest {

    @Mock
    private ResultSet rs;

    @Test
    void testMap_happyPath() throws SQLException, ResultSetMapperException {
        final Client expectedClient = new Client(1, "Sergey", "Ivanov");
        doReturn (Boolean.TRUE).when(rs).next();
        doReturn(expectedClient.getId()).when(rs).getInt("id");
        doReturn(expectedClient.getFirstName()).when(rs).getString("first_name");
        doReturn(expectedClient.getLastName()).when(rs).getString("last_name");

        final Client actualClient = new ClientResultSetMapper().map(rs);

        assertNotNull(actualClient);
        assertEquals(expectedClient.getId(), actualClient.getId());
        assertEquals(expectedClient.getFirstName(), actualClient.getFirstName());
        assertEquals(expectedClient.getLastName(), actualClient.getLastName());

        verify(rs).next();
        verify(rs).getInt("id");
        verify(rs).getString("first_name");
        verify(rs).getString("last_name");

        verifyNoMoreInteractions(rs);
    }

    @Test
    void testMap_resultSetMapperException() throws SQLException {
        final Client expectedClient = new Client(1, "Sergey", "Sergeev");

        doReturn(Boolean.TRUE).when(rs).next();
        doReturn(expectedClient.getId()).when(rs).getInt("id");
        doThrow(SQLException.class).when(rs).getString("first_name");

        final Exception errorMessage = assertThrows(ResultSetMapperException.class, () -> {
            final Client actualClient = new ClientResultSetMapper().map(rs);
        });

        Assertions.assertEquals(errorMessage.getMessage(), "Failed to map client");
        verify(rs).next();
        verifyNoMoreInteractions(rs);
    }
}