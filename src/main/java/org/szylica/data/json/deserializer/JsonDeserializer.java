package org.szylica.json.deserializer;

public interface JsonDeserializer<T> {
    T fromJson(String filename);
}
