package by.sfp.dao;

import java.util.List;

public interface GetAllByParentIdDao<T> {
    List<T> execute(Long parentId);
}
