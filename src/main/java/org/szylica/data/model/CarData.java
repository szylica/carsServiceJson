package org.szylica.data.model;

import lombok.Getter;
import org.szylica.model.car.Car;
import org.szylica.model.color.Color;

import java.math.BigDecimal;
import java.util.List;

public record CarData(
        String brand,
        String model,
        int speed,
        Color color,
        BigDecimal price,
        List<String> components
) {
    public Car toCar() {
        return new Car(brand, model, speed, color, price, components);
    }
}
