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
import org.szylica.repository.CarsRepository;
import org.szylica.service.impl.CarsServiceImpl;

import java.util.List;
import java.util.stream.Stream;

import static org.szylica.service.ExampleData.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.STRICT_STUBS)
public class CarServiceImplFindAllBySpeedBetweenTest {

    @Mock
    private CarsRepository carsRepository;

    @InjectMocks
    private CarsServiceImpl carsService;


    static Stream<Arguments> speedRangesAndCars() {
        return Stream.of(
                Arguments.of(120, 150, List.of()),
                Arguments.of(180, 180, List.of(car6)),
                Arguments.of(200, 300, List.of(car1, car3, car4, car5, car7)),
                Arguments.of(150, 300, List.of(car1, car2, car3, car4, car5, car6, car7)),
                Arguments.of(150, 210, List.of(car1, car2, car3, car4, car6)),
                Arguments.of(195, 225, List.of(car1, car3, car4, car5)),
                Arguments.of(225, 195, List.of(car1, car3, car4, car5))
        );
    }


    @Test
    @DisplayName("When speed is negative")
    void test1(){

        Assertions.assertThatThrownBy(() -> carsService.carsWithSpeedInRange(-200, 200))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Speed range must be greater than or equal to 0");

    }

    @ParameterizedTest
    @MethodSource("speedRangesAndCars")
    @DisplayName("When speed is correct")
    void test2(int minSpeed, int maxSpeed, List<Car> expectedCars){

        Mockito.when(carsRepository.getAllCars()).thenReturn(allCars);

        var actualCars = carsService.carsWithSpeedInRange(minSpeed, maxSpeed);

        Assertions.assertThat(actualCars).containsExactlyElementsOf(expectedCars);
    }

}
