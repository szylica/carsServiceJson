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
import org.szylica.repository.CarsRepository;
import org.szylica.service.impl.CarsServiceImpl;
import org.szylica.service.records.MinMax;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Stream;

import static org.szylica.model.car.CarMapper.*;
import static org.szylica.service.ExampleData.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class CarServiceImplGroupAndFindMinMaxByCriteriaTest {

    @Mock
    private CarsRepository carsRepository;

    @InjectMocks
    private CarsServiceImpl carsService;

    static Stream<Arguments> testCases() {
        return Stream.of(
                Arguments.of(carToBrand, carToSpeed, Comparator.naturalOrder(), Map.ofEntries(
                        Map.entry("BMW", new MinMax<>(List.of(car2),List.of(car3))),
                        Map.entry("AUDI",new MinMax<>(List.of(car4),List.of(car5))),
                        Map.entry("MERCEDES",new MinMax<>(List.of(car6),List.of(car7)))
                ))
        );
    }

    @ParameterizedTest
    @MethodSource("testCases")
    @DisplayName("should group by first argument and find min and max" +
            " objects determined by second argument and comparator ")
    <T, U> void test1(Function<Car, T> groupingFn,
                      Function<Car, U> minMaxGroupingFn,
                      Comparator<U> minMaxComparator,
                      Map<T, MinMax<List<Car>>> estimatedReturn){

        Mockito.when(carsRepository.getAllCars()).thenReturn(ExampleData.allCars);

        var actualReturn = carsService.groupAndFindMinMaxByCriteria(groupingFn, minMaxGroupingFn, minMaxComparator);

        Assertions.assertThat(actualReturn).containsExactlyInAnyOrderEntriesOf(estimatedReturn);


    }

    @Test
    @DisplayName("should throw exception when first argument is null")
    void test2(){
        Assertions.assertThatThrownBy(() -> carsService
                        .groupAndFindMinMaxByCriteria(null, carToSpeed, Comparator.naturalOrder()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("groupingFn cannot be null");
    }

    @Test
    @DisplayName("should throw exception when second argument is null")
    void test3(){
        Assertions.assertThatThrownBy(() -> carsService
                        .groupAndFindMinMaxByCriteria(carToBrand, null, null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("minMaxGroupingFn cannot be null");
    }

    @Test
    @DisplayName("should throw exception when third argument is null")
    void test4(){
        Assertions.assertThatThrownBy(() -> carsService
                        .groupAndFindMinMaxByCriteria(carToBrand, carToSpeed, null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("carComparator cannot be null");
    }

}
