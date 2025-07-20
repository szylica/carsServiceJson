package org.szylica.converter.car.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.szylica.converter.car.FileToCarsConverter;
import org.szylica.data.json.deserializer.JsonDeserializer;
import org.szylica.data.model.CarData;
import org.szylica.data.model.CarsData;
import org.szylica.model.car.Car;
import org.szylica.validator.Validator;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CarsDataToCarsConverterImpl implements FileToCarsConverter {

    private final JsonDeserializer<CarsData> carsDataJsonDeserializer;
    private final Validator<CarData> carDataValidator;


    @Override
    public List<Car> convert(CarsData carsData) {
        return List.of();
    }
}
