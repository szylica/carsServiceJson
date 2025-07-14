package org.szylica.model.car;

import org.szylica.model.color.Color;

import java.util.function.Function;

public interface CarMapper {
    Function<Car, Color> carToColor = car -> car.color;
}
