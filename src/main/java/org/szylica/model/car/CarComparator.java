package org.szylica.model.car;

import java.util.Comparator;

public interface CarComparator {
    Comparator<Car> brandCarComparator = Comparator.comparing(car -> car.brand);
    Comparator<Car> modelCarComparator = Comparator.comparing(car -> car.model);
    Comparator<Car> priceCarComparator = Comparator.comparing(car -> car.price);
    Comparator<Car> colorCarComparator = Comparator.comparing(car -> car.color);
    Comparator<Car> speedCarComparator = Comparator.comparing(car -> car.speed);
}
