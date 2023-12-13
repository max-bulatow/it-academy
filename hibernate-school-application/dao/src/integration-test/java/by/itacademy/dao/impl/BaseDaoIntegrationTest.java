package by.itacademy.dao.impl;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;

import java.sql.*;

public class BaseDaoIntegrationTest {

    protected interface ResultSetVerifier {

        void verify(ResultSet rs) throws SQLException;

    }

    private static Connection connection;

    @BeforeAll
    public static void beforeAll() throws SQLException {
        connection = DriverManager.getConnection(
                "jdbc:h2:mem:school",
                "postgres",
                "postgres"
        );
    }

    @AfterAll
    public static void afterAll() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }

    protected static void verifyCreatedRow(final String tableName, final Integer id, final ResultSetVerifier verifier) throws SQLException {
        final String selectSql = "select * from " + tableName + " where id = ?";

        try (PreparedStatement ps = connection.prepareStatement(selectSql)) {
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            int rowCounter = 0;
            while (rs.next()) {
                rowCounter++;

                if (rowCounter > 1) {
                    continue;
                }

                verifier.verify(rs);
            }

            if (rowCounter != 1) {
                Assertions.fail("Unexpected row number, must be 1 but is: " + rowCounter);
            }
        }
    }

}
