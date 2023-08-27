package com.example.dao.interf;

/**
 * Functional interface used to retrieve data from a database.
 * @param <S> - source object for mapping
 * @param <T> - mapping object
 */
@FunctionalInterface
public interface Mapper<S, T> {
    T map(S source);
}

