package by.itacademy.dao.datasource;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JdbcDataSourcePropertiesTest {

    final static JdbcDataSourceProperties jdbcDataSourceProperties = new JdbcDataSourceProperties(
            "postgres",
            "postgres",
            "jdbc:postgresql://localhost:5432/autoservice",
            "org.postgresql.Driver"
    );

    @Test
    void getUser_happyPath() {
        final String expectedUser = "postgres";

        final String actualUser = jdbcDataSourceProperties.getUser();

        assertEquals(expectedUser, actualUser);
    }

    @Test
    void getPassword_happyPath() {
        final String expectedPassword = "postgres";

        final String actualPassword = jdbcDataSourceProperties.getPassword();

        assertEquals(expectedPassword, actualPassword);
    }

    @Test
    void getUrl_happyPath() {
        final String expectedUrl = "jdbc:postgresql://localhost:5432/autoservice";

        final String actualUrl = jdbcDataSourceProperties.getUrl();

        assertEquals(expectedUrl, actualUrl);
    }

    @Test
    void getDriver_happyPath() {
        final String expectedDriver = "org.postgresql.Driver";

        final String actualDriver = jdbcDataSourceProperties.getDriver();

        assertEquals(expectedDriver, actualDriver);
    }
}