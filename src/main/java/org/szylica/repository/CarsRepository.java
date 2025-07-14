package org.szylica.repository;

import org.springframework.stereotype.Component;
import org.szylica.model.car.Car;

import java.util.List;

@Component
public interface CarsRepository {

    void addAllCars(List<Car> cars);
    void addCar(Car car);
    void removeCar(int id);
    Car getCar(int id);
    List<Car> getAllCars();

}
