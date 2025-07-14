package org.szylica.json.serializer.generic;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.szylica.json.converter.JsonConverter;
import org.szylica.json.serializer.JsonSerializer;

import java.io.FileWriter;
import java.io.IOException;

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
