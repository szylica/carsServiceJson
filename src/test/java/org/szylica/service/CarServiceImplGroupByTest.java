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

import java.util.List;
import java.util.Map;

import static org.szylica.service.ExampleData.allCars;
import static org.szylica.service.ExampleData.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.STRICT_STUBS)
public class CarServiceImplGroupByTest {

    @Mock
    CarsRepository carsRepository;

    @InjectMocks
    CarsServiceImpl carsService;

    @Test
    @DisplayName("Group cars by color")
    void test1(){

        Mockito.when(carsRepository.getAllCars()).thenReturn(allCars);

        var estimatedGroupedCars = Map.ofEntries(
                Map.entry(Color.BLACK, List.of(car1, car4, car6, car7)),
                Map.entry(Color.WHITE, List.of(car5)),
                Map.entry(Color.RED, List.of(car2)),
                Map.entry(Color.GREEN, List.of(car3))
        );

        Assertions.assertThat(carsService.groupBy(CarMapper.carToColor)).containsExactlyInAnyOrderEntriesOf(estimatedGroupedCars);
    }
}
