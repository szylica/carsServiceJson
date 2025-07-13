package org.szylica.model.car.comparator;

import org.szylica.model.car.Car;

import java.util.Comparator;

public interface CarComparator {
    Comparator<Car> brandCarComparator = Comparator.comparing(car -> car.brand);

}
