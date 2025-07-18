package org.szylica.model.car;

import org.szylica.model.color.Color;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.Function;

public interface CarMapper {
    Function<Car, Color> carToColor = car -> car.color;
    Function<Car, String> carToBrand = car -> car.brand;
    Function<Car, BigDecimal> carToPrice = car -> car.price;
    Function<Car, Integer> carToSpeed = car -> car.speed;
    Function<Car, List<String>> carToComponents = car -> car.components;
}
