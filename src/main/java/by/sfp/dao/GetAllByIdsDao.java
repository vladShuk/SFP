package by.sfp.dao;

import java.util.List;
import java.util.Set;

public interface GetAllByIdsDao<T> {
    Set<T> execute(List<Long> ids);
}
