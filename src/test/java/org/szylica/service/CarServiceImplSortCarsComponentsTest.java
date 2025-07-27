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
import org.szylica.data.model.CarData;
import org.szylica.model.car.Car;
import org.szylica.model.color.Color;
import org.szylica.repository.CarsRepository;
import org.szylica.service.impl.CarsServiceImpl;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.STRICT_STUBS)
public class CarServiceImplSortCarsComponentsTest {

    @Mock
    CarsRepository carsRepository;

    @InjectMocks
    CarsServiceImpl carsServiceImpl;

    @Test
    @DisplayName("Should throw exception when arguments is null")
    void test1(){

        Assertions.assertThatThrownBy(() -> carsServiceImpl.sortCarsComponents(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("comparator cannot be null");
    }

    @Test
    @DisplayName("Sorts Car's component list")
    void test2(){

        Mockito.when(carsRepository.getAllCars()).thenReturn(ExampleData.simpleCarsData);

        var carsWithSortedComponents = carsServiceImpl.sortCarsComponents(Comparator.naturalOrder());

        var estimatedListOfCars = List.of(
                new Car("BMW", "X1", 200, Color.BLACK, BigDecimal.valueOf(2000), List.of("BREAKS", "WHEELS")),
                new Car("AUDI", "RS5", 220, Color.WHITE, BigDecimal.valueOf(2500), List.of("AC", "ROOF")),
                new Car("MERCEDES", "S2", 180, Color.BLACK, BigDecimal.valueOf(1900), List.of("CUP HOLDER", "HEATED SEATS")
                ));

        Assertions.assertThat(carsWithSortedComponents).isEqualTo(estimatedListOfCars);

    }

}
