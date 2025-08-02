package org.model.car;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.szylica.service.ExampleData;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.szylica.service.ExampleData.car1;

public class CarCalculatePriceDifferenceTest {

    @Test
    @DisplayName("when car's price is lower than other price")
    void test1(){
        assertThat(car1.calculatePriceDifference(BigDecimal.valueOf(2100)))
                .isEqualTo(BigDecimal.valueOf(100));
    }

    @Test
    @DisplayName("when car's price is greater than other price")
    void test2(){
        assertThat(car1.calculatePriceDifference(BigDecimal.valueOf(1900)))
                .isEqualTo(BigDecimal.valueOf(100));
    }
}
