package org.szylica.json.deserializer.impl;

import org.szylica.json.converter.JsonConverter;
import org.szylica.json.deserializer.JsonDeserializer;
import org.szylica.json.deserializer.generic.AbstractJsonDeserializer;
import org.szylica.model.car.Car;

public class CarJsonDeserializer extends AbstractJsonDeserializer<Car> implements JsonDeserializer<Car> {
    public CarJsonDeserializer(JsonConverter<Car> jsonConverter) {
        super(jsonConverter);
    }
}
