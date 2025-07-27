package org.szylica.service;

import org.szylica.model.car.Car;
import org.szylica.service.records.MinMax;
import org.szylica.service.records.MostAndLeastExpensiveCars;
import org.szylica.service.records.PriceAndSpeedStats;
import org.szylica.service.records.Statistics;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;

public interface CarsService {

    List<Car> sortCarsByCriterium(Comparator<Car> carComparator, boolean ascending);

    List<Car> carsWithSpeedInRange(int from, int to);

    List<Car> findAllBy(Predicate<Car> criterion);

    <T> Map<T, List<Car>> groupBy(Function<Car, T> classifier);

    <T> Map<T, Long> countCars(Function<Car, T> classifier);

    <T, U> Map<T, MinMax<List<Car>>> groupAndFindMinMaxByCriteria
            (Function<Car, T> groupingFn, Function<Car, U> minMaxGroupingFn, Comparator<U> minMaxComparator);

    Map<String, MostAndLeastExpensiveCars> getMostAndLeastExpensiveCarsForBrands();

    <T extends Comparable<T>> Statistics<T> getCarsStatistics(Function<Car, T> classifier);

    PriceAndSpeedStats getPriceAndSpeedStatistics();

    List<Car> sortCarsComponents(Comparator<String> componentsComparator);

    Map<String, List<Car>> groupByComponent(Comparator<List<Car>> carsComparator);

    List<Car> findCarsByCriteria(Comparator<Car> criterion);

    List<Car> getCarsWithClosestPrice(BigDecimal price);

    List<Car> filterCars(String brand, String model, int minSpeed, int maxSpeed, BigDecimal minPrice, BigDecimal maxPrice, List<String> components);
}
