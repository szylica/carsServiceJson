package org.szylica.service;

import org.szylica.model.car.Car;
import org.szylica.model.color.Color;
import org.szylica.service.records.MostAndLeastExpensiveCars;
import org.szylica.service.records.PriceAndSpeedStats;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface CarsService {

    List<Car> sortCarsByCriterium(Criteria criteria, boolean ascending);
    Set<Car> carsWithSpeedInRange(int from, int to);
    Map<Color, Integer> countCarsByColor();
    Map<String, MostAndLeastExpensiveCars> getMostAndLeastExpensiveCarsForBrands();
    PriceAndSpeedStats getPriceAndSpeedStatistics();
    List<Car> sortCarsComponents(Comparator<String> componentsComparator);
    Map<String, List<Car>> getCarsWithComponents();
    List<Car> getCarsWithClosestPrice(BigDecimal price);
    List<Car> filterCars(String brand, String model, int minSpeed, int maxSpeed, BigDecimal minPrice, BigDecimal maxPrice, List<String> components);
}
