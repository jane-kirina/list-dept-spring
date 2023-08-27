package com.example.service.interf;

import java.util.Set;

/**
 * Main Service.
 */
public interface IService<T> {
    /**
     * Adds row in table if input entity is not already exist.
     * @param entity to add
     * @return true if object was created
     */
    boolean create(T entity);

    /**
     * Update data in database.
     * @param entity to update
     * @return true if was added
     */
    boolean update(T entity);

    /**
     * Deletes object by id.
     * @param id of object
     * @return true if the object was deleted
     */
    boolean delete(Long id);

    /**
     * Finds all data from table.
     * @return list with T
     */
    Set<T> allData();

    /**
     * Finds data about entity from database.
     * @param id of entity
     * @return entity from table
     */
    T findEntity(Long id);

    /**
     * Finds max id in table.
     * @return count
     */
    Long findMaxId();
}
