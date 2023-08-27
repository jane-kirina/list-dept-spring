package com.example.dao.connection;

import com.example.exception.ConnectionPoolException;
import com.example.exception.ResourceException;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Class for creating / connecting to pool of connections.
 */
public class ConnectionPool {
    private static final Logger LOGGER = Logger.getLogger(ConnectionPool.class);
    private static final String PROP_FILE_NAME = "hikari.properties";
    private final HikariConfig config  = new HikariConfig();
    private static HikariDataSource dataSource;

    private String jdbcUrl;
    private String driverClassName;
    private String user;
    private String pass;
    private int maximumPoolSize;
    private int minimumIdle;
    private int connectionTimeout;

    /**
     * Creates configuration to the pool and connection.
     * @return connection to hikari.
     */
    public static Connection getHikariConnection() throws ConnectionPoolException, ResourceException {
        ConnectionPool connectionPool = new ConnectionPool();
        connectionPool.getResource();
        connectionPool.getConfig();
        try {
            return dataSource.getConnection();
        } catch (SQLException exception) {
            LOGGER.error("Something went wrong with connection: " + exception.getMessage(), exception);
            throw new ConnectionPoolException(exception);
        }
    }

    private void getResource() throws ResourceException {
        try {
            Properties properties = new Properties();
            properties.load(getClass().getClassLoader().getResourceAsStream(PROP_FILE_NAME));
            jdbcUrl = properties.getProperty("jdbcUrl");
            driverClassName = properties.getProperty("driverClassName");
            user = properties.getProperty("username");
            pass = properties.getProperty("password");
            maximumPoolSize = Integer.parseInt(properties.getProperty("maximumPoolSize"));
            minimumIdle = Integer.parseInt(properties.getProperty("minimumIdle"));
            connectionTimeout = Integer.parseInt(properties.getProperty("connectionTimeout"));
        } catch (IOException exception) {
            LOGGER.error("Something went wrong with properties: " + exception.getMessage(), exception);
            throw new ResourceException(exception);
        }
    }

    private void getConfig() {
        LOGGER.debug("Set config for connection pool");

        config.setDriverClassName(driverClassName);
        config.setJdbcUrl(jdbcUrl);
        config.setUsername(user);
        config.setPassword(pass);
        config.setMaximumPoolSize(maximumPoolSize);
        config.setMinimumIdle(minimumIdle);
        config.setConnectionTimeout(connectionTimeout);

        LOGGER.debug("Get HikariDataSource");
        dataSource = new HikariDataSource(config);
    }
}
