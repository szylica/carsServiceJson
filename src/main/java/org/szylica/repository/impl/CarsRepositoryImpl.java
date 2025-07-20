package org.szylica.repository.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.szylica.model.car.Car;
import org.szylica.repository.CarsRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CarsRepositoryImpl implements CarsRepository {

    private final Map<Integer, Car> cars = new HashMap<>();

    @Override
    public void addAllCars(List<Car> cars) {
        cars.forEach(this::addCar);
    }

    @Override
    public void addCar(Car car) {
        cars.put(cars.keySet().stream().max(Integer::compareTo).orElse(0)+1, car);
    }

    @Override
    public void removeCar(int id) {
        cars.remove(id);
    }

    @Override
    public Car getCar(int id) {
        return cars.get(id);
    }

    @Override
    public List<Car> getAllCars() {
        return new ArrayList<>(cars.values());

    }
}
