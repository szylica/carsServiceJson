package org.szylica.service.records;

import org.szylica.model.car.Car;

import java.util.List;

public record MostAndLeastExpensiveCars(List<Car> mostExpensiveCars, List<Car> leastExpensiveCars) {
}
