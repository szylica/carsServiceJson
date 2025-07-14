package org.szylica.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.szylica.model.car.Car;
import org.szylica.model.car.CarComparator;
import org.szylica.model.car.CarMapper;
import org.szylica.model.color.Color;
import org.szylica.repository.CarsRepository;
import org.szylica.service.CarsService;
import org.szylica.service.Criteria;
import org.szylica.service.records.MostAndLeastExpensiveCars;
import org.szylica.service.records.PriceAndSpeedStats;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class CarsServiceImpl implements CarsService {

    @Autowired
    private final CarsRepository carsRepository;

    @Override
    public List<Car> sortCarsByCriterium(Criteria criteria, boolean ascending) {
        var comparator = CarComparator.brandCarComparator;
        switch(criteria) {
            case BRAND -> {}
            case MODEL -> comparator = CarComparator.modelCarComparator;
            case SPEED -> comparator = CarComparator.speedCarComparator;
            case COLOR -> comparator = CarComparator.colorCarComparator;
            case PRICE -> comparator = CarComparator.priceCarComparator;
        }


        var sortedCars = new ArrayList<>(carsRepository
                .getAllCars()
                .stream()
                .sorted(comparator
                        .thenComparing(CarComparator.brandCarComparator)
                        .thenComparing(CarComparator.modelCarComparator)
                        .thenComparing(CarComparator.speedCarComparator)
                        .thenComparing(CarComparator.colorCarComparator)
                        .thenComparing(CarComparator.priceCarComparator))
                .toList());

        if(!ascending) {
            Collections.reverse(sortedCars);
            return sortedCars;
        }

        return sortedCars;
    }

    @Override
    public Set<Car> carsWithSpeedInRange(int from, int to) {

        if(from < 0 || to < 0) {
            throw new IllegalArgumentException("Speed range must be greater than or equal to 0");
        }

        return carsRepository.getAllCars().stream()
                .filter(car -> car.hasSpeedInRange(from, to))
                .collect(Collectors.toSet());
    }

    @Override
    public Map<Color, Integer> countCarsByColor() {
        return carsRepository.getAllCars()
                .stream()
                .collect(Collectors.groupingBy(
                        CarMapper.carToColor,
                        Collectors.collectingAndThen(
                                Collectors.counting(),
                                Long::intValue
                        )));
    }

    @Override
    public Map<String, MostAndLeastExpensiveCars> getMostAndLeastExpensiveCarsForBrands() {
        return Map.of();
    }

    @Override
    public PriceAndSpeedStats getPriceAndSpeedStatistics() {
        return null;
    }

    @Override
    public List<Car> sortCarsComponents(Comparator<String> componentsComparator) {
        return List.of();
    }

    @Override
    public Map<String, List<Car>> getCarsWithComponents() {
        return Map.of();
    }

    @Override
    public List<Car> getCarsWithClosestPrice(BigDecimal price) {
        return List.of();
    }

    @Override
    public List<Car> filterCars(String brand, String model, int minSpeed, int maxSpeed, BigDecimal minPrice, BigDecimal maxPrice, List<String> components) {
        return List.of();
    }
}
