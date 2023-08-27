package com.example.dao.connection;

import com.example.dao.impl.DepartmentDAO;
import com.example.dao.impl.EmployeeDAO;
import com.example.exception.ConnectionPoolException;
import com.example.exception.ResourceException;
import org.apache.log4j.Logger;

import java.sql.Connection;

/**
 * Singleton to create and work with one instance of connection in connection pool and DAO.
 */
public class SingletonConnection {
    private static final Logger LOGGER = Logger.getLogger(SingletonConnection.class);
    private static SingletonConnection instance;

    /**
     * Static method for getting a connection instance.
     */
    public static Connection getConnection() {
        LOGGER.debug("Get connection to database");
        try {
            return ConnectionPool.getHikariConnection();
        } catch (ConnectionPoolException | ResourceException exception) {
            LOGGER.error("Something went wrong: " + exception.getMessage(), exception);
        }
        return null;
    }

    public static SingletonConnection getInstance() {
        if (instance == null) {
            instance = new SingletonConnection();
        }
        return instance;
    }

    /** Getters for Dao. */
    public EmployeeDAO getEmployeeDao() {
        return new EmployeeDAO(getConnection());
    }

    public DepartmentDAO getDepartmentDao() {
        return new DepartmentDAO(getConnection());
    }
}
