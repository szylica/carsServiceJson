package org.szylica.validator;

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

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AppTestBeansConfig.class)
@TestPropertySource("classpath:application-test.properties")
public class ValidatorTest {

    @Autowired
    private Validator<CarData> validator;

    @Test
    @DisplayName("when validation is ok")
    void test1() {
        var carData = new CarData("AUDI",
                "Q7",
                200,
                Color.BLACK,
                BigDecimal.ONE,
                List.of("A", "B"));

        assertThat(Validator.validate(carData, validator)).isTrue();
    }

    @Test
    @DisplayName("when validation is not ok")
    void test2() {
        var carData = new CarData("AUDi",
                "Q7",
                200,
                Color.BLACK,
                BigDecimal.ONE,
                List.of("A", "B"));

        assertThat(Validator.validate(carData, validator)).isFalse();
    }

}