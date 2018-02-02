package by.sfp.mapping;

import java.util.List;

public interface MappingList<T, R> {
    List<T> toObject(List<R> from);
}
