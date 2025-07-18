package org.szylica.service;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.szylica.model.car.Car;
import org.szylica.model.car.CarComparator;
import org.szylica.model.color.Color;
import org.szylica.repository.CarsRepository;
import org.szylica.service.impl.CarsServiceImpl;
import org.szylica.service.records.MostAndLeastExpensiveCars;
import org.szylica.service.records.PriceAndSpeedStats;
import org.szylica.service.records.PriceStatistics;
import org.szylica.service.records.SpeedStatistics;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.szylica.service.ExampleData.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.STRICT_STUBS)
public class CarsServiceImplTest {

    @Mock
    private CarsRepository carsRepository;

    @InjectMocks
    private CarsServiceImpl carsService;

    @Test
    void shouldSortCarsByPriceAsc() {

        //Given

        List<Car> estimatedSortedByPriceAsc = ExampleData.allCars.stream()
                .sorted(CarComparator.priceCarComparator
                        .thenComparing(CarComparator.brandCarComparator)
                        .thenComparing(CarComparator.modelCarComparator)
                        .thenComparing(CarComparator.speedCarComparator)
                        .thenComparing(CarComparator.colorCarComparator)
                        .thenComparing(CarComparator.priceCarComparator))
                .toList();
        Mockito.when(carsRepository.getAllCars()).thenReturn(ExampleData.allCars);


        //When

        var sortedCarsByPrice = carsService.sortCarsByCriterium(Criteria.PRICE, true);

        //Then
        Assertions
                .assertThat(sortedCarsByPrice).containsExactlyElementsOf(estimatedSortedByPriceAsc);

        Mockito.verify(carsRepository, Mockito.times(1)).getAllCars();
    }

    @Test
    void shouldSortCarsByPriceDesc() {

        //Given

        List<Car> sortedByPriceDesc = ExampleData.allCars.stream()
                .sorted(CarComparator.priceCarComparator
                        .thenComparing(CarComparator.brandCarComparator)
                        .thenComparing(CarComparator.modelCarComparator)
                        .thenComparing(CarComparator.speedCarComparator)
                        .thenComparing(CarComparator.colorCarComparator)
                        .thenComparing(CarComparator.priceCarComparator)
                        .reversed())
                .toList();
        Mockito.when(carsRepository.getAllCars()).thenReturn(ExampleData.allCars);


        //When

        var sortedCarsByBrand = carsService.sortCarsByCriterium(Criteria.PRICE, false);

        //Then
        Assertions
                .assertThat(sortedCarsByBrand)
                .isEqualTo(sortedByPriceDesc);

        Mockito.verify(carsRepository, Mockito.times(1)).getAllCars();

    }

    @Test
    void shouldReturnCarsWithSpeedInRange() {
        //Given
        var estimatedCars = Set.of(car3, car5);
        Mockito.when(carsRepository.getAllCars()).thenReturn(ExampleData.allCars);

        //When
        var filteredCars = carsService.carsWithSpeedInRange(210, 230);

        //Then
        Assertions.assertThat(filteredCars)
                .isEqualTo(estimatedCars);

        Mockito.verify(carsRepository, Mockito.times(1)).getAllCars();
    }

    @Test
    void shouldReturnCarsWithSpeedInRangeReversed() {
        //Given
        var estimatedCars = Set.of(car3, car5);
        Mockito.when(carsRepository.getAllCars()).thenReturn(ExampleData.allCars);

        //When
        var filteredCars = carsService.carsWithSpeedInRange(230, 210);

        //Then
        Assertions.assertThat(filteredCars)
                .isEqualTo(estimatedCars);

        Mockito.verify(carsRepository, Mockito.times(1)).getAllCars();
    }

    @Test
    void shouldThrowExceptionWhenArgumentIsWrong() {

        //Given
        //Mockito.when(carsRepository.getAllCars()).thenReturn(ExampleData.allCars);

//        junit
//        assertThrows(IllegalArgumentException.class, () -> {
//            carsService.carsWithSpeedInRange(-100, 200);
//        });

        //When / Then
        Assertions.assertThatThrownBy(() -> carsService.carsWithSpeedInRange(-200, 200))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Speed range must be greater than or equal to 0");

        Mockito.verify(carsRepository, Mockito.times(0)).getAllCars();
    }

    @Test
    void shouldReturnCountedCarsByColor() {

        //Given
        Mockito.when(carsRepository.getAllCars()).thenReturn(allCars);
        var estimatedCountedCars = Map.ofEntries(
                Map.entry(Color.BLACK, 4),
                Map.entry(Color.WHITE, 1),
                Map.entry(Color.RED, 1),
                Map.entry(Color.GREEN, 1)
        );

        //When
        var countedCars = carsService.countCarsByColor();

        //Then
        Assertions.assertThat(countedCars).isEqualTo(estimatedCountedCars);
        Mockito.verify(carsRepository, Mockito.times(1)).getAllCars();
    }

    @Test
    void shouldReturnEmptyMapWhenCarsIsEmpty() {

        //Given
        Mockito.when(carsRepository.getAllCars()).thenReturn(List.of());
        var estimatedCountedCars = Map.of();

        //When
        var countedCars = carsService.countCarsByColor();

        //Then
        Assertions.assertThat(countedCars).isEqualTo(estimatedCountedCars);
        Mockito.verify(carsRepository, Mockito.times(1)).getAllCars();
    }

    @Test
    void shouldReturnMapOfMostAndLeastExpensiveCars() {

        //Given
        Mockito.when(carsRepository.getAllCars()).thenReturn(allCars);
        var estimatedMapOfMostAndLeastExpensiveCars = Map.ofEntries(
                Map.entry("BMW", new MostAndLeastExpensiveCars(List.of(car3), List.of(car1))),
                Map.entry("AUDI", new MostAndLeastExpensiveCars(List.of(car5), List.of(car4))),
                Map.entry("MERCEDES", new MostAndLeastExpensiveCars(List.of(car7), List.of(car6)))
        );

        //When
        var carsCategorizedByBrandForMostAndLeastExpensive = carsService.getMostAndLeastExpensiveCarsForBrands();

        //Then
        Assertions.assertThat(carsCategorizedByBrandForMostAndLeastExpensive)
                .isEqualTo(estimatedMapOfMostAndLeastExpensiveCars);

        Mockito.verify(carsRepository, Mockito.times(1)).getAllCars();

    }

    @Test
    void shouldReturnAllCarsPriceAndSpeedStatistics() {
        //Given
        Mockito.when(carsRepository.getAllCars()).thenReturn(allCars);
        var estimatedStatistics = new PriceAndSpeedStats(
                new PriceStatistics(BigDecimal.valueOf(2500.0), BigDecimal.valueOf(2100.0) ,BigDecimal.valueOf(1900.0)),
                new SpeedStatistics(270,210,180));

        //When
        var carsPriceAndSpeedStatistics = carsService.getPriceAndSpeedStatistics();

        //Then
        Assertions.assertThat(carsPriceAndSpeedStatistics).isEqualTo(estimatedStatistics);
        Mockito.verify(carsRepository, Mockito.times(1)).getAllCars();
    }

    @Test
    void shouldReturnEmptyStatsWhenCarsIsEmpty() {
        //Given
        Mockito.when(carsRepository.getAllCars()).thenReturn(List.of());
        var estimatedStatistics = new PriceAndSpeedStats(
                new PriceStatistics(BigDecimal.valueOf(0.0), BigDecimal.valueOf(0.0) ,BigDecimal.valueOf(0.0)),
                new SpeedStatistics(0,0.0,0));

        //When
        var carsPriceAndSpeedStatistics = carsService.getPriceAndSpeedStatistics();

        //Then
        Assertions.assertThat(carsPriceAndSpeedStatistics).isEqualTo(estimatedStatistics);
        Mockito.verify(carsRepository, Mockito.times(1)).getAllCars();
    }


    // TODO
//    @Test
//    void shouldReturnListOfCarsWithSortedComponentsByLengthAscending() {
//        //Given
//        Mockito.when(carsRepository.getAllCars()).thenReturn(allCars);
//        Comparator<String> lengthComparator = Comparator.comparing(String::length);
//        var estimatedListOfCars = List.of();
//
//        //When
//        var carsWithSortedComponents = carsService.sortCarsComponents(lengthComparator);
//
//        //Then
//
//    }


    //TODO problem z sortowaniem elementów o takiej samej długości
    // (lista 1 oraz lista 2 mają tyle samo elementów, w tescie kolejnosc list to 1, 2 a w wyniku 2, 1)
    // jedyne kryterium jakie ma być brane pod uwagę to kolejność według długości listy
    // więc jak obie listy są tak samo długie to jest to bez znaczenia która będzie pierwsza
//    @Test
//    void shouldReturnComponentsAndCarsOwningThisComponents() {
//        //Given
//        Mockito.when(carsRepository.getAllCars()).thenReturn(allCars);
//        var estimatedMap = Map.ofEntries(
//                Map.entry("BREAKS", List.of(car1, car2, car7)),
//                Map.entry("WHEELS", List.of(car1, car3)),
//                Map.entry("STEERING WHEEL", List.of(car2)),
//                Map.entry("RADIO", List.of(car3)),
//                Map.entry("AC", List.of(car4, car5)),
//                Map.entry("ROOF", List.of(car5)),
//                Map.entry("CUP HOLDER", List.of(car6)),
//                Map.entry("HEATED SEATS", List.of(car6))
//        );
//
//        //When
//        var categorizedCarsByComponents = carsService.getCarsWithComponents();
//
//        //Then
//        Assertions.assertThat(categorizedCarsByComponents).isEqualTo(estimatedMap);
//        Mockito.verify(carsRepository, Mockito.times(1)).getAllCars();
//
//    }

    @Test
    void shouldReturnCarsWithClosestPrice() {
        //Given
        Mockito.when(carsRepository.getAllCars()).thenReturn(allCars);
        var estimatedCars = List.of(car1, car2, car4, car7);

        //When
        var closestPriceCars = carsService.getCarsWithClosestPrice(BigDecimal.valueOf(2050));

        //Then
        Assertions.assertThat(closestPriceCars).isEqualTo(estimatedCars);
        Mockito.verify(carsRepository, Mockito.times(1)).getAllCars();

    }

    @Test
    void shouldReturnFilteredCars() {
        //Given
        Mockito.when(carsRepository.getAllCars()).thenReturn(allCars);
        var estimatedFilteredCars = List.of(car2, car3);

        //When
        var filteredCars = carsService.filterCars("BMW",
                null,
                100,
                300,
                BigDecimal.valueOf(2100),
                BigDecimal.valueOf(2500),
                List.of("BREAKS", "WHEELS"));

        //Then
        Assertions.assertThat(filteredCars).isEqualTo(estimatedFilteredCars);
        Mockito.verify(carsRepository, Mockito.times(1)).getAllCars();

    }


}
