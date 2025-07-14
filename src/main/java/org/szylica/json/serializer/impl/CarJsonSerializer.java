package org.szylica.json.serializer.impl;

import org.szylica.json.converter.JsonConverter;
import org.szylica.json.serializer.JsonSerializer;
import org.szylica.json.serializer.generic.AbstractJsonSerializer;
import org.szylica.model.car.Car;

public final class CarJsonSerializer extends AbstractJsonSerializer<Car> implements JsonSerializer<Car> {

    public CarJsonSerializer(JsonConverter<Car> converter) {
        super(converter);
    }
}
