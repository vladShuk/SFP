package by.sfp.dao;

public interface GetByNameDao<T> {
    T execute(String name);
}
