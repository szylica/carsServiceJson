package org.szylica.data.json.deserializer.impl;

import org.szylica.data.json.converter.JsonConverter;
import org.szylica.data.json.deserializer.JsonDeserializer;
import org.szylica.data.json.deserializer.generic.AbstractJsonDeserializer;
import org.szylica.data.model.CarData;

public class CarDataJsonDeserializer extends AbstractJsonDeserializer<CarData> implements JsonDeserializer<CarData> {
    public CarDataJsonDeserializer(JsonConverter<CarData> jsonConverter) {
        super(jsonConverter);
    }
}
