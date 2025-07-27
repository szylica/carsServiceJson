package org.szylica.model.car;


import lombok.*;
import org.szylica.model.color.Color;

import java.math.BigDecimal;
import java.util.Comparator;
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

    public Car sortComponents(Comparator<String> componentsComparator) {
        return new Car(
                this.brand,
                this.model,
                this.speed,
                this.color,
                this.price,
                this.components.stream().sorted(componentsComparator).toList()
        );
    }

    /**
     * Calculates the difference in price between this car and another price.
     * This method computes the absolute difference between the price of this car and another price value provided
     * as an argument. The difference is returned as a BigDecimal, ensuring precision in calculation.
     *
     * @param otherPrice The price to be compared with this car's price.
     * @return The absolute difference in price as a BigDecimal.
     */
    public BigDecimal calculatePriceDifference(BigDecimal otherPrice) {
        return this.price.subtract(otherPrice).abs();
    }
}
