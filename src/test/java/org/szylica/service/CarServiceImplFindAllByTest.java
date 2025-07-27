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
import org.szylica.model.car.CarPredicates;
import org.szylica.repository.CarsRepository;
import org.szylica.service.impl.CarsServiceImpl;

import java.util.List;

import static org.szylica.model.car.CarPredicates.hasSpeedInRangePredicate;
import static org.szylica.service.ExampleData.allCars;
import static org.szylica.service.ExampleData.car6;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class CarServiceImplFindAllByTest {

    @Mock
    private CarsRepository carsRepository;

    @InjectMocks
    private CarsServiceImpl carsService;

    @Test
    @DisplayName("when cars contain speed in range")
    void test1(){
        Mockito.when(carsRepository.getAllCars()).thenReturn(allCars);

        Assertions.assertThat(carsService.findAllBy(hasSpeedInRangePredicate(150, 180))).isEqualTo(List.of(car6));
    }

    @Test
    @DisplayName("when cars don't contain speed in range")
    void test2(){
        Mockito.when(carsRepository.getAllCars()).thenReturn(allCars);

        Assertions.assertThat(carsService.findAllBy(hasSpeedInRangePredicate(350, 380))).isEmpty();
    }


}
