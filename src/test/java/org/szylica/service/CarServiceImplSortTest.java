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

import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.szylica.model.car.CarComparator.*;
import static org.szylica.service.ExampleData.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.STRICT_STUBS)
public class CarServiceImplSortTest {

    //private static final List<Car> CARS = ExampleData.simpleCarsData;

    @Mock
    private CarsRepository carsRepository;

    @InjectMocks
    private CarsServiceImpl carsService;


    static Stream<Arguments> comparatorsWithSortedCars() {
        return Stream.of(
                Arguments.of(priceCarComparator, List.of(car6, car1, car5)),
                Arguments.of(brandCarComparator, List.of(car5, car1, car6)),
                Arguments.of(modelCarComparator, List.of(car5, car6, car1)),
                Arguments.of(speedCarComparator, List.of(car6, car1, car5)),
                Arguments.of(colorCarComparator, List.of(car5, car1, car6))
        );
    }

    @Test
    @DisplayName("when comparator is null")
    void test1(){
        Assertions.assertThatThrownBy(() -> carsService.sortCarsByCriterium(null, false))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("carComparator cannot be null");
    }

    @ParameterizedTest
    @MethodSource("comparatorsWithSortedCars")
    @DisplayName("When comparator is not null")
    void test2(Comparator<Car> comparator, List<Car> expectedSortedCars) {

        Mockito.when(carsRepository.getAllCars()).thenReturn(simpleCarsData);

        var actualReturn = carsService.sortCarsByCriterium(comparator, true);

        Assertions.assertThat(actualReturn).isEqualTo(expectedSortedCars);

    }
}
