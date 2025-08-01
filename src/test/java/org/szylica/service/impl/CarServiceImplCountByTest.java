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
import org.szylica.model.car.CarMapper;
import org.szylica.model.color.Color;
import org.szylica.repository.CarsRepository;
import org.szylica.service.impl.CarsServiceImpl;

import java.util.Map;

import static org.szylica.service.ExampleData.allCars;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.STRICT_STUBS)
public class CarServiceImplCountByTest {

    @Mock
    CarsRepository carsRepository;

    @InjectMocks
    CarsServiceImpl carsService;

    @Test
    @DisplayName("Count cars by color")
    void test1(){

        Mockito.when(carsRepository.getAllCars()).thenReturn(allCars);

        var estimatedCountedCars = Map.ofEntries(
                Map.entry(Color.BLACK, 4L),
                Map.entry(Color.WHITE, 1L),
                Map.entry(Color.RED, 1L),
                Map.entry(Color.GREEN, 1L)
        );

        Assertions.assertThat(carsService.countCars(CarMapper.carToColor)).containsExactlyInAnyOrderEntriesOf(estimatedCountedCars);
    }
}
