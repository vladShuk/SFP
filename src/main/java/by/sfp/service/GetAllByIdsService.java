package by.sfp.service;

import java.util.List;
import java.util.Set;

public interface GetAllByIdsService<T> {
    Set<T> execute(List<Long> ids);
}
