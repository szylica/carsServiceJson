package org.szylica.data.json.serializer.impl;

import org.szylica.data.json.converter.JsonConverter;
import org.szylica.data.json.serializer.JsonSerializer;
import org.szylica.data.json.serializer.generic.AbstractJsonSerializer;
import org.szylica.data.model.CarData;
import org.szylica.model.car.Car;

public final class CarDataJsonSerializer extends AbstractJsonSerializer<CarData> implements JsonSerializer<CarData> {

    public CarDataJsonSerializer(JsonConverter<CarData> converter) {
        super(converter);
    }
}
