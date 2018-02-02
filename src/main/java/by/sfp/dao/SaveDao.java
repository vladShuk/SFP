package by.sfp.dao;

public interface SaveDao<T> {
    void execute(T toSave);
}
