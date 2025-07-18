package org.szylica.json.serializer;

public interface JsonSerializer<T> {

    void toJson(T data, String filename);
}
