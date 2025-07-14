package org.szylica.model.car;

import org.szylica.validator.Validator;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class CarValidator implements Validator<Car> {

    private final Map<String, String> errors = new HashMap<>();

    private final String stringRegex = "^[A-Z\\d\\s]+$";

    @Override
    public Map<String, String> validate(Car car) {

        if(car.price == null) {
            errors.put("price", "Price cannot be null");
        }
        else if(car.price.compareTo(BigDecimal.ZERO) < 0) {
            errors.put("price", "Price must be greater than zero");
        }

        if(car.brand == null) {
            errors.put("brand", "Brand cannot be null");
        }
        else if(car.brand.matches(stringRegex)) {
            errors.put("brand", "Brand doesn't match regex");
        }

        if(car.model == null) {
            errors.put("model", "Model cannot be null");
        }
        else if(car.model.matches(stringRegex)) {
            errors.put("model", "Model doesn't match regex");
        }

        if(car.color == null) {
            errors.put("color", "Color cannot be null");
        }

        if(car.speed < 0){
            errors.put("speed", "Speed cannot be negative");
        }

        if(car.components == null) {
            errors.put("components", "Components cannot be null");
        }
        else if(car.components.stream().anyMatch(component -> !component.matches(stringRegex))) {
            errors.put("components", "Some component doesn't match regex");
        }

        return errors;
    }
}
