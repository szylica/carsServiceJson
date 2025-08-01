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
import org.szylica.data.model.CarData;
import org.szylica.model.car.Car;
import org.szylica.model.car.CarComparator;
import org.szylica.repository.CarsRepository;
import org.szylica.repository.impl.CarsRepositoryImpl;
import org.szylica.service.impl.CarsServiceImpl;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import static org.szylica.model.car.CarComparator.priceCarComparator;
import static org.szylica.service.ExampleData.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.STRICT_STUBS)
public class CarServiceFindCarsByCriteriaTest {

    @Mock
    CarsRepository carsRepository;

    @InjectMocks
    CarsServiceImpl carsServiceImpl;

    static Stream<Arguments> testCases() {
        return Stream.of(
                Arguments.of(BigDecimal.valueOf(2100), List.of(car2)),
                Arguments.of(BigDecimal.valueOf(2050), List.of(car1, car2, car4, car7)),
                Arguments.of(BigDecimal.valueOf(1000), List.of(car6))
        );
    }

    @Test
    @DisplayName("When comparator is null")
    void test1(){
        Assertions.assertThatThrownBy(() -> carsServiceImpl.findCarsByCriteria(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("criterion cannot be null");
    }


    @ParameterizedTest
    @MethodSource("testCases")
    @DisplayName("when the comparator checks the smallest difference between the car's price and another specified price")
    void test2(BigDecimal value, List<Car> estimatedCars){

        Mockito.when(carsRepository.getAllCars()).thenReturn(ExampleData.allCars);

        Comparator<Car> carPriceComparator = Comparator.comparing(car -> car.calculatePriceDifference(value));

        var cars = carsServiceImpl.findCarsByCriteria(carPriceComparator);

        Assertions.assertThat(cars).containsExactlyInAnyOrderElementsOf(estimatedCars);
    }

}
