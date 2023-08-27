package com.example.dao.impl.decorator;
import com.example.dao.interf.Mapper;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Abstract class, for implementing decorator pattern for DAO.
 * Allows you to dynamically add new functionality to objects, wrapping them in "wrappers",
 * reuse methods for working with the database, adding to them only a query, entities and a connection to the database.
 * @param <T> type of entity
 */
public abstract class AbstractDecoratorDao<T> {
    private static final String MESSAGE = "Getting data from table by statement with one parameter: {0}; by queries: {1}";
    private static final Logger LOGGER = Logger.getLogger(AbstractDecoratorDao.class);
    protected Mapper<ResultSet, T> mapperFromSql;
    protected Connection connection;

    /**
     * Finds all in table.
     * @param queries to table
     * @return set of entities from database, table
     */
    protected Set<T> findAll(String queries) {
        Set<T> set = new LinkedHashSet<>();

        LOGGER.debug("Getting data from table by queries: " + queries);

        try (PreparedStatement statement = connection.prepareStatement(queries)) {

            ResultSet resultSet = statement.executeQuery();

            LOGGER.debug("Mapping data: " + mapperFromSql.toString());
            while (resultSet.next()) {
                set.add(mapperFromSql.map(resultSet));
            }

            LOGGER.debug("Close resources: find all");
            resultSet.close();
            connection.close();
        } catch (SQLException exception) {
            LOGGER.error("Something went wrong. There is no way to get all the data from the database: "
                    + exception.getMessage(), exception);
        }
        return set;
    }

    /**
     * Statement with one parameter for database.
     * @param id of key in table
     * @param queries to table
     * @return true, if object was deleted from database
     */
    protected boolean statementWithParameter(Long id, String queries) {
        LOGGER.debug(MessageFormat.format(MESSAGE, id, queries));
        try (PreparedStatement statement = connection.prepareStatement(queries)) {
            statement.setLong(1, id);

            statement.executeUpdate();

            LOGGER.debug("Close resources: expression statement");
            connection.close();
        } catch (SQLException exception) {
            LOGGER.error("Something went wrong. Request failed: " + exception.getMessage(), exception);
            return false;
        }
        return true;
    }

    /**
     * Find count by expression
     * @param expression of key in table
     * @param queries to table
     * @return count
     */
    protected int findCountByExpression(String expression, String queries) {
        LOGGER.debug(MessageFormat.format(MESSAGE, expression, queries));
        int count = 0;
        try (PreparedStatement statement = connection.prepareStatement(queries)) {
            statement.setString(1, expression);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                count = resultSet.getInt("count");
            }

            LOGGER.debug("Close resources: find count");
            resultSet.close();
            connection.close();
        } catch (SQLException exception) {
            LOGGER.error("Something went wrong. Couldn't find count: " + exception.getMessage(), exception);
        }
        return count;
    }

    /**
     * Finds max in table.
     * @param queries to table
     * @return max
     */
    protected Long findMax(String queries) {
        LOGGER.debug("Getting max from table by queries: " + queries);
        long count = -1;
        try (PreparedStatement statement = connection.prepareStatement(queries)) {
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getLong("max");
            }

            LOGGER.debug("Close resources: find max");
            resultSet.close();
            connection.close();
        } catch (SQLException exception) {
            LOGGER.error("Something went wrong. Couldn't find max: " + exception.getMessage(), exception);
        }
        return count;
    }

    /**
     * Find entity by id table.
     * @param queries to table
     * @param id of entity
     * @return entity
     */
    protected T findById(String queries, Long id, T entity) {
        LOGGER.debug("Getting object by id: " + id + "; by queries: " + queries);
        try (PreparedStatement statement = connection.prepareStatement(queries)) {
            statement.setLong(1, id);

            ResultSet resultSet = statement.executeQuery();

            resultSet.next();
            LOGGER.debug("Mapping entity: " + mapperFromSql.toString());
            entity = mapperFromSql.map(resultSet);

            LOGGER.debug("Close resources: find by id");
            resultSet.close();
            connection.close();
        } catch (SQLException exception) {
            LOGGER.error("Something went wrong. Couldn't find object by id: " + exception.getMessage(), exception);
        }
        return entity;
    }
}
