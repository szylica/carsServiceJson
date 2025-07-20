package org.szylica.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.szylica.data.json.converter.JsonConverter;
import org.szylica.data.json.converter.impl.GsonConverter;
import org.szylica.data.json.deserializer.JsonDeserializer;
import org.szylica.data.json.deserializer.impl.CarsDataJsonDeserializer;
import org.szylica.data.model.CarData;
import org.szylica.data.model.CarsData;
import org.szylica.validator.Validator;
import org.szylica.validator.impl.CarDataValidator;

@Configuration
@ComponentScan("org.szylica")
public class AppTestBeansConfig {

    @Bean
    public Gson gson(){
        return new GsonBuilder().setPrettyPrinting().create();
    }

    @Bean
    public JsonConverter<CarsData> jsonConverter(Gson gson){
        return new GsonConverter<>(gson);
    }

    @Bean
    public JsonDeserializer<CarsData> jsonDeserializer(JsonConverter<CarsData> jsonConverter){
        return new CarsDataJsonDeserializer(jsonConverter);
    }

    @Bean
    public Validator<CarData> carDataValidator(){
        return new CarDataValidator();
    }
}
