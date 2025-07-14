package org.szylica.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.szylica.json.converter.impl.GsonConverter;
import org.szylica.json.deserializer.impl.CarJsonDeserializer;
import org.szylica.json.deserializer.impl.CarRepositoryJsonDeserializer;
import org.szylica.json.serializer.impl.CarJsonSerializer;
import org.szylica.json.serializer.impl.CarRepositoryJsonSerializer;
import org.szylica.model.car.Car;
import org.szylica.model.color.Color;
import org.szylica.repository.impl.CarsRepositoryImpl;

import java.math.BigDecimal;
import java.util.List;

@Configuration
public class AppBeansConfig {

    @Bean
    public Car car(){
        return new Car("BMW", "X1", 200, Color.WHITE, BigDecimal.valueOf(10), List.of("BREAKS"));
    }

    @Bean
    public Gson gsonObject(){
        return new GsonBuilder().setPrettyPrinting().create();
    }

    @Bean
    public GsonConverter<Car> gsonCarConverter(){
        return new GsonConverter<Car>(gsonObject());
    }

    @Bean
    public CarJsonSerializer carJsonSerializer(){
        return new CarJsonSerializer(gsonCarConverter());
    }

    @Bean
    public CarJsonDeserializer carJsonDeserializer(){
        return new CarJsonDeserializer(gsonCarConverter());
    }

    @Bean
    public GsonConverter<CarsRepositoryImpl> gsonCarRepositoryConverter(){
        return new GsonConverter<CarsRepositoryImpl>(gsonObject());
    }

    @Bean
    public CarRepositoryJsonSerializer carRepositoryJsonSerializer(){
        return new CarRepositoryJsonSerializer(gsonCarRepositoryConverter());
    }

    @Bean
    public CarRepositoryJsonDeserializer carRepositoryJsonDeserializer(){
        return new CarRepositoryJsonDeserializer(gsonCarRepositoryConverter());
    }

}
