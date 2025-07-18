package org.szylica.model.car;


import lombok.*;
import org.szylica.model.color.Color;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;

@RequiredArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class Car {

    final String brand;
    final String model;
    final int speed;
    final Color color;
    final BigDecimal price;
    @Getter
    final List<String> components;

    public boolean hasSpeedInRange(int from, int to) {

        if (from > to){
            var a = from;
            from = to;
            to = a;
        }

        return speed >= from && speed <= to;
    }

    public boolean isMoreExpensiveThan(BigDecimal price) {
        return price.compareTo(this.price) <= 0;
    }

    public boolean hasComponent(String component) {
        return components.contains(component);
    }

    public boolean hasComponents(List<String> componentsList) {
        return new HashSet<>(componentsList).containsAll(components);
    }
}
