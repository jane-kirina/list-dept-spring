package com.example.dao.interf;

import com.example.exception.database.AddInDatabaseException;
import com.example.exception.database.DeleteInDatabaseException;
import com.example.exception.database.UpdateDataInDatabaseException;

import java.util.Set;

/**
 * Main interface for DAO.
 */
public interface IDao<T>  {
    /**
     * Adds row in table if input entity is not already exist.
     * @param entity to add
     * @param id of new entity
     * @return true if was added
     */
    boolean saveEntity(T entity, Long id) throws AddInDatabaseException;

    /**
     * Updates the data for an entity in a table.
     * @param entity to be updated
     * @return true if the table has been updated
     */
    boolean updateEntity(T entity) throws UpdateDataInDatabaseException;

    /**
     * Removes an object by it's id.
     * @param id object to delete
     * @return true, if object was delete
     */
    boolean deleteEntity(Long id) throws DeleteInDatabaseException;

    /**
     * Finds all data from table.
     * @return list of all data in table
     */
    Set<T> findAll();

    /**
     * Finds entity in table by id.
     * @param id of entity
     * @return entity from database
     */
    T findEntity(Long id);

    /**
     * Finds max id in table.
     * @return count of rows
     */
    Long findMaxId();

    /**
     * Finds an object by a name column in a table.
     * @param name key
     * @return count of rows wit
     */
    int findNameCount(String name);
}
