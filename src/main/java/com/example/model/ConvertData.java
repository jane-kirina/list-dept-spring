package com.example.model;

/**
 * Functional interface used to retrieve entity and pojo.
 * @param <S> - source object for mapping
 * @param <T> - mapping object
 */
@FunctionalInterface
public interface ConvertData<S, T> {
    T map(S source);
}