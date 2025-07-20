package org.szylica.data.json.serializer.generic;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.szylica.data.json.converter.JsonConverter;
import org.szylica.data.json.serializer.JsonSerializer;

import java.io.FileWriter;

@RequiredArgsConstructor
public abstract class AbstractJsonSerializer<T> implements JsonSerializer<T> {
    private final JsonConverter<T> converter;

    @SneakyThrows
    @Override
    public void toJson(T data, String filename) {
        try(var writer = new FileWriter(filename)) {
            converter.toJson(data, writer);
        }
    }
}
