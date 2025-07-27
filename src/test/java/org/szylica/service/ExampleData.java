package org.szylica.service;

import org.szylica.data.model.CarData;
import org.szylica.model.car.Car;
import org.szylica.model.car.CarComparator;
import org.szylica.model.color.Color;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public interface ExampleData {

    List<String> setOfComponents1 = List.of("WHEELS", "BREAKS");
    List<String> setOfComponents2 = List.of("STEERING WHEEL", "BREAKS");
    List<String> setOfComponents3 = List.of("RADIO", "WHEELS");
    List<String> setOfComponents4 = List.of("AC");
    List<String> setOfComponents5 = List.of("ROOF", "AC");
    List<String> setOfComponents6 = List.of("CUP HOLDER", "HEATED SEATS");

    CarData carData1 = new CarData("BMW", "X1", 200, Color.BLACK, BigDecimal.valueOf(2000), setOfComponents1);

    CarData carData2 = new CarData("BMW", "X2", 190, Color.RED, BigDecimal.valueOf(2100), setOfComponents2);

    CarData carData3 = new CarData("BMW", "X3", 210, Color.GREEN, BigDecimal.valueOf(2200), setOfComponents3);

    CarData carData4 = new CarData("AUDI", "RS1", 200, Color.BLACK, BigDecimal.valueOf(2000), setOfComponents4);

    CarData carData5 = new CarData("AUDI", "RS5", 220, Color.WHITE, BigDecimal.valueOf(2500), setOfComponents5);

    CarData carData6 = new CarData("MERCEDES", "S2", 180, Color.BLACK, BigDecimal.valueOf(1900), setOfComponents6);

    CarData carData7 = new CarData("MERCEDES", "S3", 270, Color.BLACK, BigDecimal.valueOf(2000), setOfComponents2);

    Car car1 = carData1.toCar();

    Car car2 = carData2.toCar();
    Car car3 = carData3.toCar();
    Car car4 = carData4.toCar();

    Car car5 = carData5.toCar();
    Car car6 = carData6.toCar();

    Car car7 = carData7.toCar();

    List<Car> simpleCarsData = List.of(car1, car5, car6);


    List<Car> allCars = List.of(car1, car2, car3, car4, car5, car6, car7);


}
