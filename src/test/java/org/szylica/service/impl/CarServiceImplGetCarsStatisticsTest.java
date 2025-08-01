package org.szylica.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.szylica.model.car.Car;
import org.szylica.model.car.CarMapper;
import org.szylica.model.color.Color;
import org.szylica.repository.CarsRepository;
import org.szylica.service.impl.CarsServiceImpl;
import org.szylica.service.records.Statistics;

import java.math.BigDecimal;
import java.util.function.Function;
import java.util.stream.Stream;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.STRICT_STUBS)
public class CarServiceImplGetCarsStatisticsTest {

    @Mock
    CarsRepository carsRepository;

    @InjectMocks
    CarsServiceImpl carsServiceImpl;

    private static Stream<Arguments> testCases(){
        return Stream.of(
                Arguments.of(CarMapper.carToColor, new Statistics<>(Color.WHITE, Color.GREEN, BigDecimal.ZERO)),
                Arguments.of(CarMapper.carToSpeed, new Statistics<>(180, 270, BigDecimal.valueOf(210))),
                Arguments.of(CarMapper.carToPrice, new Statistics<>(BigDecimal.valueOf(1900), BigDecimal.valueOf(2500), BigDecimal.valueOf(2100)))
        );
    }

    @Test
    @DisplayName("when classifier is null")
    void test1(){

        Assertions.assertThatThrownBy(() -> carsServiceImpl.getCarsStatistics(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("classifier cannot be null");
    }

    @ParameterizedTest
    @MethodSource("testCases")
    @DisplayName("when classifier is not null")
    void test2(Function classifier, Statistics<?> estimatedReturn){

        Mockito.when(carsRepository.getAllCars()).thenReturn(ExampleData.allCars);

        Assertions.assertThat(carsServiceImpl.getCarsStatistics(classifier)).isEqualTo(estimatedReturn);
    }

}
