package by.sfp.service;

public interface GetByIdService<T> {
    T execute(Long id);
}
