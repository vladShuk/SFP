package by.sfp.dao;

public interface GetByIdDao<T> {
    T execute(Long id);
}
