package by.itacademy.dao.jdbc;

import by.itacademy.dao.Dao;
import by.itacademy.dao.DaoException;
import by.itacademy.dao.datasource.JdbcDataSourceProperties;
import by.itacademy.dao.jdbc.mapper.ResultSetMapper;

import java.sql.*;

public abstract class GenericJdbcDao<T> implements Dao<T> {

    private final JdbcDataSourceProperties properties;
    private final String tableName;
    private final ResultSetMapper<T> mapper;
    private final String leftJoinTableName;


    public GenericJdbcDao(
            final JdbcDataSourceProperties properties,
            final String tableName,
            final ResultSetMapper<T> mapper,
            final String leftJoinTableName
    ) throws DaoException {
        this.properties = properties;
        this.tableName = tableName;
        this.mapper = mapper;
        this.leftJoinTableName = leftJoinTableName;

        registerDriver(properties.getDriver());
    }

    protected abstract PreparedStatement getPrepareStatementForCreate(Connection cn, T model) throws SQLException;

    @Override
    public Integer create(final T model) throws JdbcDaoException {
        try (final Connection connection = createConnection()) {
            final PreparedStatement ps = getPrepareStatementForCreate(connection, model);
            ps.executeUpdate();

            final ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }

            throw new JdbcDaoException("Failed to retrieve id", null);
        } catch (final Exception ex) {
            throw new JdbcDaoException("Failed to create: " + tableName, ex);
        }
    }

    @Override
    public T read(final Integer id) throws JdbcDaoException {
        try (final Connection connection = createConnection()) {
            final String readSql = "select * from %s where id = ?".formatted(tableName);
            final PreparedStatement ps = connection.prepareStatement(readSql);
            ps.setInt(1, id);

            final ResultSet rs = ps.executeQuery();

            return mapper.map(rs);
        } catch (final Exception ex) {
            throw new JdbcDaoException("Failed to read: " + tableName, ex);
        }
    }

    @Override
    public T readWithLeftJoin(final Integer id, String leftJoinTableName) throws DaoException {
        try (final Connection connection = createConnection()) {
            final String readSql = "select * from %s left join %s on %s.%s_id = %s.id where %s.id = ?".formatted(tableName, leftJoinTableName, tableName, leftJoinTableName, leftJoinTableName, tableName);
            final PreparedStatement ps = connection.prepareStatement(readSql);
            ps.setInt(1, id);

            final ResultSet rs = ps.executeQuery();

            return mapper.map(rs);
        } catch (final Exception ex) {
            throw new DaoException("Failed to read: " + tableName, ex);
        }
    }

    private Connection createConnection() throws JdbcDaoException {
        try {
            return DriverManager.getConnection(
                    properties.getUrl(),
                    properties.getUser(),
                    properties.getPassword()
            );
        } catch (final SQLException ex) {
            throw new JdbcDaoException("Failed to create connection ", ex);
        }
    }

    private static void registerDriver(final String driverName) throws JdbcDaoException {
        try {
            final Class<?> driverClass = Class.forName(driverName);

            final boolean isNotRegistered = DriverManager.drivers().noneMatch(dr -> dr.getClass().equals(driverClass));
            if (isNotRegistered) {
                final Driver driver = (Driver) driverClass.getDeclaredConstructor().newInstance();
                DriverManager.registerDriver(driver);
            }
        } catch (final Exception ex) {
            throw new JdbcDaoException("Failed to register driver: " + driverName, ex);
        }
    }

}
