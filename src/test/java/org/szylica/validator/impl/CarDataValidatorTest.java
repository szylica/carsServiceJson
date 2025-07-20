package org.szylica.validator.impl;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.szylica.config.AppTestBeansConfig;
import org.szylica.data.model.CarData;
import org.szylica.model.color.Color;
import org.szylica.validator.Validator;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AppTestBeansConfig.class)
@TestPropertySource("classpath:application-test.properties")
public class CarDataValidatorTest {

    @Autowired
    private Validator<CarData> validator;

    static Stream<Arguments> validationData() {
        return Stream.of(
                Arguments.of(
                        new CarData("AUDi",
                                "Q7",
                                200,
                                Color.BLACK,
                                BigDecimal.ONE,
                                List.of("A", "B")),
                        Map.of("brand", "Brand doesn't match regex")
                ),
                Arguments.of(
                        new CarData(null,
                                "Q7",
                                200,
                                Color.BLACK,
                                BigDecimal.ONE,
                                List.of("A", "B")),
                        Map.of("brand", "Brand cannot be null")
                ),
                Arguments.of(
                        new CarData("AUDI",
                                "Q7a",
                                200,
                                Color.BLACK,
                                BigDecimal.ONE,
                                List.of("A", "B")),
                        Map.of("model", "Model doesn't match regex")
                ),
                Arguments.of(
                        new CarData("AUDI",
                                null,
                                200,
                                Color.BLACK,
                                BigDecimal.ONE,
                                List.of("A", "B")),
                        Map.of("model", "Model cannot be null")
                ),
                Arguments.of(
                        new CarData("AUDI",
                                "Q7",
                                -200,
                                Color.BLACK,
                                BigDecimal.ONE,
                                List.of("A", "B")),
                        Map.of("speed", "Speed cannot be negative")
                ),
                Arguments.of(
                        new CarData("AUDI",
                                "Q7",
                                200,
                                null,
                                BigDecimal.ONE,
                                List.of("A", "B")),
                        Map.of("color", "Color cannot be null")
                ),
                Arguments.of(
                        new CarData("AUDI",
                                "Q7",
                                200,
                                Color.BLACK,
                                BigDecimal.valueOf(-1),
                                List.of("A", "B")),
                        Map.of("price", "Price must be greater than zero")
                ),
                Arguments.of(
                        new CarData("AUDI",
                                "Q7",
                                200,
                                Color.BLACK,
                                null,
                                List.of("A", "B")),
                        Map.of("price", "Price cannot be null")
                ),
                Arguments.of(
                        new CarData("AUDI",
                                "Q7",
                                200,
                                Color.BLACK,
                                BigDecimal.ONE,
                                null),
                        Map.of("components", "Components cannot be null")
                ),
                Arguments.of(
                        new CarData("AUDI",
                                "Q7",
                                200,
                                Color.BLACK,
                                BigDecimal.ONE,
                                List.of("Aa", "B")),
                        Map.of("components", "Some component doesn't match regex")
                )


        );
    }


    @ParameterizedTest
    @MethodSource("validationData")
    @DisplayName("when validation result is not correct")
    void test1(CarData carData, Map<String, String> errorMessages) {
        assertThat(validator.validate(carData))
                .isEqualTo(errorMessages);
    }

}
