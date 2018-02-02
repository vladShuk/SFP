package by.sfp.service;

public interface SaveService<T> {
    void execute(T toSave);
}
