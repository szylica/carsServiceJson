package org.szylica.data.model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.szylica.model.car.Car;
import org.szylica.model.color.Color;

import java.math.BigDecimal;
import java.util.List;

public class CarDataTest {

    @Test
    @DisplayName("when conversion from car data to car works")
    void test1() {
        var carData = new CarData(
                "AUDI",
                "Q8",
                200,
                Color.BLACK,
                BigDecimal.ONE,
                List.of("A", "B"));

        var expectedCar = new Car(
                "AUDI",
                "Q8",
                200,
                Color.BLACK,
                BigDecimal.ONE,
                List.of("A", "B"));

        Assertions.assertThat(carData.toCar()).isEqualTo(expectedCar);
    }
}
