package org.model.car;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.szylica.service.ExampleData.*;
import static org.szylica.service.ExampleData.car1;
import static org.szylica.service.ExampleData.car2;
import static org.szylica.service.ExampleData.car3;
import static org.szylica.service.ExampleData.car4;
import static org.szylica.service.ExampleData.car5;
import static org.szylica.service.ExampleData.car6;
import static org.szylica.service.ExampleData.car7;

public class CarHasSpeedBetweenTest {

    static Stream<Arguments> speedRangesAndCars() {
        return Stream.of(
                Arguments.of(190, 220, true),
                Arguments.of(180, 190, true),
                Arguments.of(150, 220, true),
                Arguments.of(220, 190, true),
                Arguments.of(190, 180, true),
                Arguments.of(220, 150, true),
                Arguments.of(110, 120, false),
                Arguments.of(120, 110, false)
        );
    }

    @ParameterizedTest
    @MethodSource("speedRangesAndCars")
    @DisplayName("Should chec if car's speed falls within the specified range")
    void test1(int minSpeed, int maxSpeed, boolean expected) {

        Assertions.assertThat(car2.hasSpeedInRange(minSpeed, maxSpeed)).isEqualTo(expected);

    }

}
