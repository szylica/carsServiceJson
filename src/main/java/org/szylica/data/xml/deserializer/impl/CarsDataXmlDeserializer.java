package org.szylica.data.xml.deserializer.impl;

import org.springframework.stereotype.Component;
import org.szylica.data.model.CarsData;
import org.szylica.data.xml.converter.XmlConverter;
import org.szylica.data.xml.deserializer.generic.AbstractXmlDeserializer;

@Component
public class CarsDataXmlFileDeserializer extends AbstractXmlDeserializer<CarsData> {
    public CarsDataXmlFileDeserializer(XmlConverter<CarsData> converter) {
        super(converter);
    }
}
