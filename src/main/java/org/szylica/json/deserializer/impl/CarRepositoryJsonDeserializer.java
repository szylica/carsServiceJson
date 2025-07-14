package org.szylica.json.deserializer.impl;

import org.szylica.json.converter.JsonConverter;
import org.szylica.json.deserializer.JsonDeserializer;
import org.szylica.json.deserializer.generic.AbstractJsonDeserializer;
import org.szylica.repository.CarsRepository;
import org.szylica.repository.impl.CarsRepositoryImpl;

public class CarRepositoryJsonDeserializer extends AbstractJsonDeserializer<CarsRepositoryImpl> implements JsonDeserializer<CarsRepositoryImpl> {


    public CarRepositoryJsonDeserializer(JsonConverter<CarsRepositoryImpl> jsonConverter) {
        super(jsonConverter);
    }
}
