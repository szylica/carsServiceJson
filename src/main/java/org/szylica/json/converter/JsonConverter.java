package org.szylica.json.converter;

import com.google.gson.stream.JsonWriter;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public interface JsonConverter<T> {

    void toJson(T object, FileWriter fileWriter) throws IOException;
    T fromJson(FileReader fileReader, Class<T> tClass) throws IOException;

}
