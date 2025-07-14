package org.szylica.json.serializer.impl;

import org.szylica.repository.impl.CarsRepositoryImpl;
import org.szylica.json.converter.JsonConverter;
import org.szylica.json.serializer.JsonSerializer;
import org.szylica.json.serializer.generic.AbstractJsonSerializer;

public class CarRepositoryJsonSerializer extends AbstractJsonSerializer<CarsRepositoryImpl> implements JsonSerializer<CarsRepositoryImpl> {
    public CarRepositoryJsonSerializer(JsonConverter<CarsRepositoryImpl> converter) {
        super(converter);
    }
}
