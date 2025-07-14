package org.szylica.service;

import org.szylica.model.car.Car;
import org.szylica.model.car.CarComparator;
import org.szylica.model.color.Color;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public interface ExampleData {

    List<String> setOfComponents1 = List.of("BREAKS", "WHEELS");
    List<String> setOfComponents2 = List.of("STEERING WHEEL", "BREAKS");
    List<String> setOfComponents3 = List.of("RADIO", "WHEELS");
    List<String> setOfComponents4 = List.of("AC");
    List<String> setOfComponents5 = List.of("ROOF", "AC");
    List<String> setOfComponents6 = List.of("CUP HOLDER", "HEATED SEATS");

    Car car1 = new Car("BMW", "X1", 200, Color.BLACK, BigDecimal.valueOf(2000), setOfComponents1);

    Car car2 = new Car("BMW", "X2", 190, Color.RED, BigDecimal.valueOf(2100), setOfComponents2);

    Car car3 = new Car("BMW", "X3", 210, Color.GREEN, BigDecimal.valueOf(2200), setOfComponents3);

    Car car4 = new Car("AUDI", "RS1", 200, Color.BLACK, BigDecimal.valueOf(2000), setOfComponents4);

    Car car5 = new Car("AUDI", "RS5", 220, Color.WHITE, BigDecimal.valueOf(2500), setOfComponents5);

    Car car6 = new Car("MERCEDES", "S2", 180, Color.BLACK, BigDecimal.valueOf(1900), setOfComponents6);

    Car car7 = new Car("MERCEDES", "S3", 270, Color.BLACK, BigDecimal.valueOf(2000), setOfComponents2);

    List<Car> allCars = List.of(car1, car2, car3, car4, car5, car6, car7);


}
