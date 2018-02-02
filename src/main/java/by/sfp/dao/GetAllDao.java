package by.sfp.dao;

import java.util.List;

public interface GetAllDao<T> {
    List<T> execute();
}
