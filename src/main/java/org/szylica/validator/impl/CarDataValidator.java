package org.szylica.validator.impl;

import com.google.gson.internal.bind.util.ISO8601Utils;
import org.springframework.beans.factory.annotation.Value;
import org.szylica.data.model.CarData;
import org.szylica.validator.Validator;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class CarDataValidator implements Validator<CarData> {



    @Value("${validation.regex}")
    private String regex;


    //private final String stringRegex = "^[A-Z\\d\\s]+$";

    @Override
    public Map<String, String> validate(CarData carData) {

        Map<String, String> errors = new HashMap<>();

        if(carData.price() == null) {
            errors.put("price", "Price cannot be null");
        }
        else if(carData.price().compareTo(BigDecimal.ZERO) < 0) {
            errors.put("price", "Price must be greater than zero");
        }

        if(carData.brand() == null) {
            errors.put("brand", "Brand cannot be null");
        }
        else if(!carData.brand().matches(regex)) {
            errors.put("brand", "Brand doesn't match regex");
        }

        if(carData.model() == null) {
            errors.put("model", "Model cannot be null");
        }
        else if(!carData.model().matches(regex)) {
            errors.put("model", "Model doesn't match regex");
        }

        if(carData.color() == null) {
            errors.put("color", "Color cannot be null");
        }

        if(carData.speed() < 0){
            errors.put("speed", "Speed cannot be negative");
        }

        if(carData.components() == null) {
            errors.put("components", "Components cannot be null");
        }
        else if(carData.components().stream().anyMatch(component -> !component.matches(regex))) {
            errors.put("components", "Some component doesn't match regex");
        }

        return errors;
    }

}
