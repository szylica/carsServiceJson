package org.szylica.data.json.deserializer.impl;

import org.springframework.stereotype.Component;
import org.szylica.data.json.converter.JsonConverter;
import org.szylica.data.json.deserializer.JsonDeserializer;
import org.szylica.data.json.deserializer.generic.AbstractJsonDeserializer;
import org.szylica.repository.impl.CarsRepositoryImpl;

@Component
public class CarRepositoryJsonDeserializer extends AbstractJsonDeserializer<CarsRepositoryImpl> implements JsonDeserializer<CarsRepositoryImpl> {


    public CarRepositoryJsonDeserializer(JsonConverter<CarsRepositoryImpl> jsonConverter) {
        super(jsonConverter);
    }
}
