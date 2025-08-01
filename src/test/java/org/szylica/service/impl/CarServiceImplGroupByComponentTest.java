package org.szylica.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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
import java.util.Map;

import static org.szylica.service.ExampleData.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class CarServiceImplGroupByComponentTest {

    @Mock
    CarsRepository carsRepository;

    @InjectMocks
    CarsServiceImpl carsServiceImpl;


    @Test
    @DisplayName("Should throw exception when arguments is null")
    void test1(){

        Assertions.assertThatThrownBy(() -> carsServiceImpl.groupByComponent(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("comparator cannot be null");
    }

/*
    List<String> setOfComponents1 = List.of("WHEELS", "BREAKS");
    List<String> setOfComponents5 = List.of("ROOF", "AC");
    List<String> setOfComponents6 = List.of("CUP HOLDER", "HEATED SEATS");

    CarData carData1 = new CarData("BMW", "X1", 200, Color.BLACK, BigDecimal.valueOf(2000), setOfComponents1);
    CarData carData5 = new CarData("AUDI", "RS5", 220, Color.WHITE, BigDecimal.valueOf(2500), setOfComponents5);
    CarData carData6 = new CarData("MERCEDES", "S2", 180, Color.BLACK, BigDecimal.valueOf(1900), setOfComponents6);
*/

    @Test
    @DisplayName("Should group Cars by components they have and sort by amount of cars")
    void test2(){

        Mockito.when(carsRepository.getAllCars()).thenReturn(ExampleData.simpleCarsData);

        Comparator<List<Car>> comparator = Comparator.comparingInt(List::size);

        Map<String, List<Car>> expectedCars = Map.ofEntries(
                Map.entry("WHEELS", List.of(car1)),
                Map.entry("BREAKS", List.of(car1)),
                Map.entry("ROOF", List.of(car5)),
                Map.entry("AC", List.of(car5)),
                Map.entry("CUP HOLDER", List.of(car6)),
                Map.entry("HEATED SEATS", List.of(car6))
        );

        var actualReturn = carsServiceImpl.groupByComponent(comparator);
        Assertions.assertThat(actualReturn).containsAllEntriesOf(expectedCars);
    }

}
