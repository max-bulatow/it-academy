package by.itacademy.dao.datasource;

import java.util.Objects;

public class JdbcDataSourceProperties {

    private final String user;
    private final String password;
    private final String url;
    private final String driver;

    public JdbcDataSourceProperties(
            final String user,
            final String password,
            final String url,
            final String driver
    ) {
        this.user = user;
        this.password = password;
        this.url = url;
        this.driver = driver;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public String getUrl() {
        return url;
    }

    public String getDriver() {
        return driver;
    }

    @Override
    public boolean equals(final Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        final JdbcDataSourceProperties that = (JdbcDataSourceProperties) object;
        return Objects.equals(user, that.user) && Objects.equals(password, that.password) && Objects.equals(url, that.url) && Objects.equals(driver, that.driver);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, password, url, driver);
    }
}