package org.model.car;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.szylica.model.car.Car;
import org.szylica.model.color.Color;
import org.szylica.service.ExampleData;
import org.szylica.service.records.CarCriteria;

import java.math.BigDecimal;
import java.util.List;

public class CarMatchesCriteriaTest {

    @Test
    @DisplayName("when car matches criteria")
    void test1(){

        CarCriteria carCriteria = new CarCriteria(
                "^B.*",
                "^X.*",
                100,
                200,
                BigDecimal.ONE,
                BigDecimal.valueOf(2000),
                List.of("WHEELS"),
                Color.BLACK
        );

        Car car = ExampleData.car1;

        Assertions.assertThat(car.matchesCriteria(carCriteria)).isTrue();
    }

    @Test
    @DisplayName("when car does not match criteria")
    void test2(){

        CarCriteria carCriteria = new CarCriteria(
                "^B.*",
                "^X.*",
                100,
                190,
                BigDecimal.ONE,
                BigDecimal.valueOf(2000),
                List.of("WHEELS"),
                Color.BLACK
        );

        Car car = ExampleData.car1;

        Assertions.assertThat(car.matchesCriteria(carCriteria)).isFalse();

    }

}
