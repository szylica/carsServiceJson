package org.szylica.data.json.deserializer.impl;

import org.springframework.stereotype.Component;
import org.szylica.data.json.converter.JsonConverter;
import org.szylica.data.json.deserializer.JsonDeserializer;
import org.szylica.data.json.deserializer.generic.AbstractJsonDeserializer;
import org.szylica.data.model.CarData;
import org.szylica.data.model.CarsData;

@Component
public class CarsDataJsonDeserializer extends AbstractJsonDeserializer<CarsData> implements JsonDeserializer<CarsData> {
    public CarsDataJsonDeserializer(JsonConverter<CarsData> jsonConverter) {
        super(jsonConverter);
    }
}
