package org.szylica.json.converter.impl;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.szylica.json.converter.JsonConverter;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


// DESIGN PATTERN ADAPTER
@RequiredArgsConstructor
public class GsonConverter<T> implements JsonConverter<T> {

    private final Gson gson;

    @Override
    public void toJson(Object object, FileWriter fileWriter) throws IOException {
        gson.toJson(object, fileWriter);
    }

    @Override
    public T fromJson(FileReader fileReader, Class<T> tClass) throws IOException {
        return gson.fromJson(fileReader, tClass);
    }
}
