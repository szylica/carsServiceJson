package org.szylica.converter;

public interface Converter<T, U> {
    U convert(T t);
}
