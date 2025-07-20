package org.szylica.data.json.serializer.impl;

import org.springframework.stereotype.Component;
import org.szylica.repository.impl.CarsRepositoryImpl;
import org.szylica.data.json.converter.JsonConverter;
import org.szylica.data.json.serializer.JsonSerializer;
import org.szylica.data.json.serializer.generic.AbstractJsonSerializer;

@Component
public class CarRepositoryJsonSerializer extends AbstractJsonSerializer<CarsRepositoryImpl> implements JsonSerializer<CarsRepositoryImpl> {
    public CarRepositoryJsonSerializer(JsonConverter<CarsRepositoryImpl> converter) {
        super(converter);
    }
}
