package org.szylica.converter.car.impl;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.szylica.config.AppTestBeansConfig;
import org.szylica.converter.car.FileToCarsConverter;
import org.szylica.data.json.deserializer.JsonDeserializer;
import org.szylica.data.model.CarData;
import org.szylica.data.model.CarsData;
import org.szylica.model.color.Color;
import org.szylica.validator.Validator;

import java.math.BigDecimal;
import java.util.List;

@ExtendWith({SpringExtension.class, MockitoExtension.class})
@ContextConfiguration(classes = AppTestBeansConfig.class)
@TestPropertySource("classpath:application-test.properties")
public class CarsDataToCarsConverterImplTest {

    @Autowired
    private Validator<CarData> carDataValidator;

    @Mock
    private JsonDeserializer<CarsData> carsDataJsonDeserializer;

    private FileToCarsConverter fileToCarsConverter;

    @BeforeEach
    void setUp() {
        fileToCarsConverter = new JsonFileToCarsConverterImpl(carsDataJsonDeserializer, carDataValidator);

    }

    @Test
    @DisplayName("when all data is correct")
    void test1() {
        Mockito.when(carsDataJsonDeserializer.fromJson(ArgumentMatchers.anyString()))
                .thenReturn(new CarsData(List.of(
                        new CarData(
                                "AUDI",
                                "Q7",
                                200,
                                Color.BLACK,
                                BigDecimal.ONE,
                                List.of("A", "B")),
                        new CarData("BMW",
                                "M3",
                                200,
                                Color.BLACK,
                                BigDecimal.ONE,
                                List.of("B", "C"))
                )));


        Assertions
                .assertThat(fileToCarsConverter.convert("cars.json"))
                .hasSize(2);
    }

    @Test
    @DisplayName("when not all data is correct")
    void test2() {

        var correctCar = new CarData("BMW",
                "M3",
                200,
                Color.BLACK,
                BigDecimal.ONE,
                List.of("B", "C"));

        Mockito.when(carsDataJsonDeserializer.fromJson(ArgumentMatchers.anyString()))
                .thenReturn(new CarsData(List.of(
                        new CarData(
                                "AUDi",
                                "Q7",
                                200,
                                Color.BLACK,
                                BigDecimal.ONE,
                                List.of("A", "B")),
                        correctCar
                )));


        Assertions
                .assertThat(fileToCarsConverter.convert("cars.json"))
                .hasSize(1)
                .containsOnly(correctCar.toCar());
    }
}
