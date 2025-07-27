package org.szylica.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.szylica.collector.CarsStatsCollector;
import org.szylica.model.car.Car;
import org.szylica.model.car.CarMapper;
import org.szylica.repository.CarsRepository;
import org.szylica.service.CarsService;
import org.szylica.service.records.MinMax;
import org.szylica.service.records.MostAndLeastExpensiveCars;
import org.szylica.service.records.PriceAndSpeedStats;
import org.szylica.service.records.Statistics;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static org.szylica.model.car.CarMapper.carToComponents;

@RequiredArgsConstructor
@Service
public class CarsServiceImpl implements CarsService {

    @Autowired
    @Qualifier("carsRepositoryImpl")
    private final CarsRepository CarsRepository;

    /**
     * Sorts a list of Car objects based on the provided criterion.
     *
     * @param carComparator The criterion for sorting Car objects. It's an objects that implements {@link Comparator<Car>}
     *                      interface, which defines how two Car objects are compared to each other.
     * @param ascending     Boolean parameter that defines if Car objects should be sorted in ascending or descending order.
     * @return A list of Car objects sorted based on the given criterion
     * @throws IllegalArgumentException if the provided comparator is null.
     */
    @Override
    public List<Car> sortCarsByCriterium(Comparator<Car> carComparator, boolean ascending) {

        if (carComparator == null) {
            throw new IllegalArgumentException("carComparator cannot be null");
        }

        if (!ascending) {
            carComparator = carComparator.reversed();
        }

        return CarsRepository
                .getAllCars()
                .stream()
                .sorted(carComparator)
                .toList();

    }

    /**
     * Retrieves a list of Car objects with speeds within the range specified by the given arguments
     *
     * @param from The minimum speed (inclusive) of the cars to be included in the returned list.
     * @param to   The maximum speed (inclusive) of the cars to be included in the returned list.
     * @return A list of Car objects with speeds falling between the provided minimum and maximum values.
     * @throws IllegalArgumentException if speed range is not correct.
     */
    @Override
    public List<Car> carsWithSpeedInRange(int from, int to) {

        if (from < 0 || to < 0) {
            throw new IllegalArgumentException("Speed range must be greater than or equal to 0");
        }

        return CarsRepository.getAllCars()
                .stream()
                .filter(car -> car.hasSpeedInRange(from, to))
                .toList();
    }


    /**
     * Find and returns a list of {@code Car} objects that match the specified criterion.
     *
     * @param criterion The predicate used to test each {@code Car} object. Only cars that satisfy this criterion
     *                  will be included in the returned list.
     * @return A listo of {@code Car} objects that match the specified criterion. If no cars meet the criterion,
     * an empty list is returned.
     * @throws IllegalArgumentException if the provided predicate is null.
     */
    @Override
    public List<Car> findAllBy(Predicate<Car> criterion) {
        if (criterion == null) {
            throw new IllegalArgumentException("criterion cannot be null");
        }
        return CarsRepository.getAllCars()
                .stream()
                .filter(criterion)
                .toList();

    }

    /**
     * Groups a collection of {@code Car} objects based on the specified classifier.
     *
     * @param classifier a Function interface that takes a {@code Car} object and returns a values of type {@code T}
     *                   that will be used as a key in the resulting map
     * @param <T>        the type of the key in the resulting map, determined by the classifier's output.
     * @return A map with keys of type {@code T} and values as lists of {@code Car} objects that have been grouped
     * by the classifier
     * @throws IllegalArgumentException if the provided function is null.
     */
    @Override
    public <T> Map<T, List<Car>> groupBy(Function<Car, T> classifier) {
        if (classifier == null) {
            throw new IllegalArgumentException("classifier cannot be null");
        }
        return CarsRepository.getAllCars()
                .stream()
                .collect(Collectors.groupingBy(classifier));
    }

    /**
     * Counts a number of {@code Car} objects based on the specified classifier.
     *
     * @param classifier a Function interface that takes a {@code Car} object and returns a values of type {@code T}
     *                   that will be used as a key in the resulting map
     * @param <T>        the type of the key in the resulting map, determined by the classifier's output.
     * @return A map with keys of type {@code T} and values representing the count of {@code Car} objects
     * that match each key
     * @throws IllegalArgumentException if the provided function is null.
     */
    @Override
    public <T> Map<T, Long> countCars(Function<Car, T> classifier) {

        if (classifier == null) {
            throw new IllegalArgumentException("classifier cannot be null");
        }

        return CarsRepository.getAllCars()
                .stream()
                .collect(Collectors.groupingBy(classifier, Collectors.counting()));
    }

    /**
     * Groups the cars by a specified criteria and then finds the minimum and maximum values
     * for each group based on another criteria.
     *
     * @param groupingFn       a function that produces the key used to group cars.
     * @param minMaxGroupingFn a function that produces the key used to group cars for each group.
     * @param minMaxComparator a comparator based on which min and max are determined.
     * @param <T>              the type of the grouping key.
     * @param <U>              the type of min and max values.
     * @return a map where keys are produced by the grouping function and values are MinMax objects containing
     * the minimum and maximum values based on the comparator for each group.
     * @throws IllegalArgumentException if any of the provided functions or comparator is null.
     * @see MinMax
     */
    @Override
    public <T, U> Map<T, MinMax<List<Car>>> groupAndFindMinMaxByCriteria(Function<Car, T> groupingFn,
                                                                         Function<Car, U> minMaxGroupingFn,
                                                                         Comparator<U> minMaxComparator) {

        if (groupingFn == null) {
            throw new IllegalArgumentException("groupingFn cannot be null");
        }

        if (minMaxGroupingFn == null) {
            throw new IllegalArgumentException("minMaxGroupingFn cannot be null");
        }

        if (minMaxComparator == null) {
            throw new IllegalArgumentException("carComparator cannot be null");
        }

        return CarsRepository.getAllCars()
                .stream()
                .collect(Collectors.groupingBy(groupingFn,
                        Collectors.collectingAndThen(
                                Collectors.groupingBy(minMaxGroupingFn),
                                map -> {
                                    var min = map.keySet()
                                            .stream()
                                            .min(minMaxComparator)
                                            .orElseThrow();

                                    var max = map.keySet()
                                            .stream()
                                            .max(minMaxComparator)
                                            .orElseThrow();

                                    return new MinMax<>(map.get(min), map.get(max));
                                }
                        )


                ));
    }

    /**
     * @return
     */
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

    /**
     * Computes the minimum, maximum and average values for a list of cars.
     * This method accepts a function that extracts a value of type T from each car.
     * It computes and returns the minimum, maximum, and average of these values.
     * The method is generic and can work with any field of the Car class that implements the Comparable interface.
     * The average is calculated specifically for BigDecimal values.
     *
     * @param classifier a function that extracts the value of type T from a Car object.
     * @param <T>        the type of the field to be analyzed. This type must implement the Comparable interface.
     * @return an instance of the {@code Statistics} class containing the min, max and average values.
     * @throws IllegalArgumentException if provided function is null.
     * @see Statistics
     */
    @Override
    public <T extends Comparable<T>> Statistics<T> getCarsStatistics(Function<Car, T> classifier) {

        if (classifier == null) {
            throw new IllegalArgumentException("classifier cannot be null");
        }

        T min = CarsRepository.getAllCars()
                .stream()
                .map(classifier)
                .min(Comparator.naturalOrder())
                .orElseGet(() -> null);

        T max = CarsRepository.getAllCars()
                .stream()
                .map(classifier)
                .max(Comparator.naturalOrder())
                .orElseGet(() -> null);

        BigDecimal avg = CarsRepository.getAllCars()
                .stream()
                .map(classifier)
                .filter(value -> value instanceof Number)
                .map(value -> {
                    if (value instanceof BigDecimal bd) {
                        return bd;
                    }
                    return new BigDecimal(value.toString());
                })
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .divide(BigDecimal.valueOf(CarsRepository.getAllCars().size()), RoundingMode.HALF_EVEN);

        return new Statistics<>(min, max, avg);
    }

    /**
     * Computes the maximum, minimum and average price and speed for cars.
     * Method uses collector to collect data from cars and compute them.
     *
     * @return {@code PriceAndSpeedStats} record which have SpeedStatistics and PriceStatistics.
     * @see PriceAndSpeedStats
     */
    @Deprecated
    @Override
    public PriceAndSpeedStats getPriceAndSpeedStatistics() {
        return CarsRepository.getAllCars()
                .stream()
                .collect(new CarsStatsCollector());
    }

    /**
     * Sorts the equipment list of each car based on the provided comparator.
     *
     * @param componentsComparator A Comparator<String> used to sort the component list of each car.
     * @return A list of Car objects with sorted components lists.
     */
    @Override
    public List<Car> sortCarsComponents(Comparator<String> componentsComparator) {

        if (componentsComparator == null) {
            throw new IllegalArgumentException("comparator cannot be null");
        }

        return CarsRepository.getAllCars()
                .stream()
                .map(car -> car.sortComponents(componentsComparator))
                .collect(Collectors.toList());
    }

    /**
     * Return a map where each key is a component, and the value is a list of cars that have this component.
     * The map is sorted based on the provided comparator for value.
     *
     * @param carsComparator A Comparator<List<Car>> used to sort the map values.
     * @return A Map<String, List<Car>> with each component and the cars that have it,
     * sorted based on the entryComparator.
     */
    @Override
    public Map<String, List<Car>> groupByComponent(Comparator<List<Car>> carsComparator) {

        if (carsComparator == null) {
            throw new IllegalArgumentException("comparator cannot be null");
        }

        return CarsRepository.getAllCars()
                .stream()
                .flatMap(car -> carToComponents
                        .apply(car)
                        .stream()
                        .map(component -> new AbstractMap.SimpleEntry<>(component, car))
                )
                .collect(Collectors.groupingBy(
                        Map.Entry::getKey,
                        Collectors.mapping(Map.Entry::getValue, Collectors.toList())
                ))
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(carsComparator))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (a, b) -> a,
                        LinkedHashMap::new));
    }

    /**
     *
     * @param criterion
     * @return
     */
    @Override
    public List<Car> findCarsByCriteria(Comparator<Car> criterion) {

        if(criterion == null) {
            throw new IllegalArgumentException("criterion cannot be null");
        }

        var minCar = CarsRepository.getAllCars()
                .stream()
                .min(criterion)
                .orElseThrow(() -> new IllegalArgumentException("Cannot find cars by criteria"));

        return CarsRepository.getAllCars()
                .stream()
                .filter(car -> criterion.compare(car, minCar) == 0)
                .toList();
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
