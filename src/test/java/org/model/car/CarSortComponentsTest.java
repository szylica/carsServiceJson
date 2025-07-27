package org.model.car;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.szylica.service.ExampleData;

import java.util.Comparator;
import java.util.List;

public class CarSortComponentsTest {



    @Test
    @DisplayName("Should return new car object with sorted component list")
    void test1(){
        var sortedCar = ExampleData.car1.sortComponents(Comparator.naturalOrder());

        Assertions.assertThat(sortedCar.getComponents()).isEqualTo(List.of("BREAKS", "WHEELS"));
    }
}
