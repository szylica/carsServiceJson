package org.szylica.data.json.deserializer.impl;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.szylica.config.AppTestBeansConfig;
import org.szylica.data.json.deserializer.JsonDeserializer;
import org.szylica.data.model.CarsData;
import org.szylica.model.car.Car;

import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AppTestBeansConfig.class)
public class CarsDataJsonDeserializerTest {

    @Autowired
    private JsonDeserializer<CarsData> jsonDeserializer;

    @Test
    @DisplayName("when data is loaded correctly")
    void test1() {
        var path = Paths
                .get("src","test","resources","cars-test.json")
                .toFile()
                .getAbsolutePath();

        var cars = jsonDeserializer.fromJson(path).cars();

        assertThat(cars).hasSize(2);

    }

}
