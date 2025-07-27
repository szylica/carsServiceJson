package org.szylica.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.szylica.data.json.converter.impl.GsonConverter;
import org.szylica.data.json.deserializer.impl.CarsDataJsonDeserializer;
import org.szylica.data.json.deserializer.impl.CarRepositoryJsonDeserializer;
import org.szylica.data.json.serializer.impl.CarsDataJsonSerializer;
import org.szylica.data.json.serializer.impl.CarRepositoryJsonSerializer;
import org.szylica.data.model.CarData;
import org.szylica.data.model.CarsData;
import org.szylica.model.car.Car;
import org.szylica.model.color.Color;
import org.szylica.repository.impl.CarsRepositoryImpl;

import java.math.BigDecimal;
import java.util.List;

@Configuration
@ComponentScan("org.szylica")
@PropertySource("classpath:application.properties")
public class AppBeansConfig {

    @Bean
    public Car car(){
        return new Car("BMW", "X1", 200, Color.WHITE, BigDecimal.valueOf(10), List.of("BREAKS"));
    }

    @Bean
    public Gson gson(){
        return new GsonBuilder().setPrettyPrinting().create();
    }

}
