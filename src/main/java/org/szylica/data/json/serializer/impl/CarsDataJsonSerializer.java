package org.szylica.data.json.serializer.impl;

import org.springframework.stereotype.Component;
import org.szylica.data.json.converter.JsonConverter;
import org.szylica.data.json.serializer.JsonSerializer;
import org.szylica.data.json.serializer.generic.AbstractJsonSerializer;
import org.szylica.data.model.CarsData;

@Component
public final class CarsDataJsonSerializer extends AbstractJsonSerializer<CarsData> implements JsonSerializer<CarsData> {

    public CarsDataJsonSerializer(JsonConverter<CarsData> converter) {
        super(converter);
    }
}
