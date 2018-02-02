package by.sfp.service;

import java.util.List;

public interface GetAllByParentIdService<T> {
    List<T> execute(Long parentId);
}
