package org.szylica.data.xml;

public interface XmlDeserializer<T> {
    T deserialize(String xml);
}
