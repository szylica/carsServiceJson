package org.szylica.data.json.deserializer.generic;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.szylica.data.json.converter.JsonConverter;
import org.szylica.data.json.deserializer.JsonDeserializer;

import java.io.FileReader;
import java.lang.reflect.ParameterizedType;

@RequiredArgsConstructor
public abstract class AbstractJsonDeserializer<T> implements JsonDeserializer<T> {

    private final JsonConverter<T> jsonConverter;

    @SuppressWarnings("unchecked")
    private final Class<T> type =
            (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    @SneakyThrows
    @Override
    public T fromJson(String filename) {
        try (var reader = new FileReader(filename)) {
            return jsonConverter.fromJson(reader, type);
        }
    }
}
