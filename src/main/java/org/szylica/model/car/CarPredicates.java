package org.szylica.model.car;

import java.util.function.Predicate;

public interface CarPredicates {
    static Predicate<Car> hasSpeedInRangePredicate(int from, int to) {
        return car -> car.hasSpeedInRange(from, to);
    }
}
