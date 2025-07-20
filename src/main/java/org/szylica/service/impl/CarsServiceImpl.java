package org.szylica.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.szylica.collector.CarsStatsCollector;
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
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class CarsServiceImpl implements CarsService {

    @Autowired
    private final CarsRepository CarsRepository;

    @Override
    public List<Car> sortCarsByCriterium(Criteria criteria, boolean ascending) {
        var comparator = CarComparator.brandCarComparator;
        switch (criteria) {
            case BRAND -> {
            }
            case MODEL -> comparator = CarComparator.modelCarComparator;
            case SPEED -> comparator = CarComparator.speedCarComparator;
            case COLOR -> comparator = CarComparator.colorCarComparator;
            case PRICE -> comparator = CarComparator.priceCarComparator;
        }


        var sortedCars = new ArrayList<>(CarsRepository
                .getAllCars()
                .stream()
                .sorted(comparator
                        .thenComparing(CarComparator.brandCarComparator)
                        .thenComparing(CarComparator.modelCarComparator)
                        .thenComparing(CarComparator.speedCarComparator)
                        .thenComparing(CarComparator.colorCarComparator)
                        .thenComparing(CarComparator.priceCarComparator))
                .toList());

        if (!ascending) {
            Collections.reverse(sortedCars);
            return sortedCars;
        }

        return sortedCars;
    }

    @Override
    public Set<Car> carsWithSpeedInRange(int from, int to) {

        if (from < 0 || to < 0) {
            throw new IllegalArgumentException("Speed range must be greater than or equal to 0");
        }

        return CarsRepository.getAllCars().stream()
                .filter(car -> car.hasSpeedInRange(from, to))
                .collect(Collectors.toSet());
    }

    @Override
    public Map<Color, Integer> countCarsByColor() {
        return CarsRepository.getAllCars()
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
        return CarsRepository.getAllCars()
                .stream()
                .collect(Collectors.groupingBy(
                        CarMapper.carToBrand,
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                this::getMaxAndLeastExpensiveCarsFromList
                        )));
    }

    @Override
    public PriceAndSpeedStats getPriceAndSpeedStatistics() {
        return CarsRepository.getAllCars()
                .stream()
                .collect(new CarsStatsCollector());
    }

    @Override
    public List<Car> sortCarsComponents(Comparator<String> componentsComparator) {
        return List.of();
    }

    @Override
    public Map<String, List<Car>> getCarsWithComponents() {
        var allUniqueComponents = CarsRepository.getAllCars()
                .stream()
                .collect(Collectors.toMap(car -> car, CarMapper.carToComponents))
                .entrySet()
                .stream()
                .flatMap(entry -> new HashSet<>(entry.getValue())
                        .stream())
                .collect(Collectors.toSet());

        return allUniqueComponents
                .stream()
                .collect(Collectors.toMap(Function.identity(), component -> CarsRepository.getAllCars().stream().filter(car -> car.hasComponent(component)).toList()))
                .entrySet()
                .stream()
                .sorted(Comparator.comparing(entry -> entry.getValue().size()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (a, b) -> a, LinkedHashMap::new));
    }

    @Override
    public List<Car> getCarsWithClosestPrice(BigDecimal price) {
        return CarsRepository.getAllCars()
                .stream()
                .collect(Collectors.toMap(
                        car -> CarMapper.carToPrice.apply(car).subtract(price).abs(),
                        car -> new ArrayList<>(Arrays.asList(car)),
                        (list1, list2) -> {
                            list1.addAll(list2);
                            return list1;
                        }
                ))
                .entrySet()
                .stream()
                .min(Map.Entry.comparingByKey())
                .map(Map.Entry::getValue)
                .orElseGet(ArrayList::new);
    }

    @Override
    public List<Car> filterCars(String brand, String model, int minSpeed, int maxSpeed, BigDecimal minPrice, BigDecimal maxPrice, List<String> components) {
        Predicate<Car> brandPredicate;
        Predicate<Car> modelPredicate;
        Predicate<Car> speedPredicate;
        Predicate<Car> pricePredicate;
        Predicate<Car> componentPredicate;

        return null;
    }

    private MostAndLeastExpensiveCars getMaxAndLeastExpensiveCarsFromList(List<Car> cars) {
        Map<BigDecimal, List<Car>> groupedByPrice = cars.stream()
                .collect(Collectors.groupingBy(CarMapper.carToPrice));

        BigDecimal max = Collections.max(groupedByPrice.keySet());
        BigDecimal min = Collections.min(groupedByPrice.keySet());

        return new MostAndLeastExpensiveCars(groupedByPrice.get(max), groupedByPrice.get(min));
    }

}
