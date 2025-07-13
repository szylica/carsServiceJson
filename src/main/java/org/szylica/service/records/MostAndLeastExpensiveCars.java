package org.szylica.service;

import org.szylica.model.car.Car;

import java.util.List;

public record MostAndLeastExpensiveCars(List<Car> mostExpensiveCars, List<Car> leastExpensiveCars) {
}
