package by.sfp.mapping;

public interface Mapping<T, R> {
    T toObject(R from);
}
