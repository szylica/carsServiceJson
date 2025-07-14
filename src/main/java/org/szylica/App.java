package org.szylica;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.szylica.config.AppBeansConfig;
import org.szylica.json.converter.impl.GsonConverter;
import org.szylica.json.deserializer.impl.CarRepositoryJsonDeserializer;
import org.szylica.json.serializer.impl.CarJsonSerializer;
import org.szylica.json.serializer.impl.CarRepositoryJsonSerializer;
import org.szylica.model.car.Car;
import org.szylica.model.color.Color;
import org.szylica.repository.CarsRepository;
import org.szylica.repository.impl.CarsRepositoryImpl;

import java.math.BigDecimal;
import java.util.List;

public class App 
{
    public static void main( String[] args )
    {
        Car car1 = Car.builder()
                .brand("BMW")
                .model("X1")
                .price(BigDecimal.valueOf(200))
                .color(Color.WHITE)
                .speed(200)
                .components(List.of("BREAKS"))
                .build();

        Car car2 = Car.builder()
                .brand("BMW")
                .model("X2")
                .price(BigDecimal.valueOf(210))
                .color(Color.BLACK)
                .speed(220)
                .components(List.of("WATER"))
                .build();

        var context = new AnnotationConfigApplicationContext(AppBeansConfig.class);

        var object = context.getBean("car", Car.class);

//        var json = gson.toJson(Car.builder()
//                .brand("BMW")
//                .model("X1")
//                .price(BigDecimal.valueOf(200))
//                .color(Color.WHITE)
//                .speed(200)
//                .components(List.of("BREAKS"))
//                .build());
//        System.out.println(json);
//
//        var car = gson.fromJson(json, Car.class);
//        System.out.println(car);

        var filename = "cars.json";

        var carJsonSerializer = context.getBean("carJsonSerializer", CarJsonSerializer.class);
        carJsonSerializer.toJson(car1, filename);

//        var repo = new CarsRepositoryImpl();
//        repo.addCar(car1);
//        repo.addCar(car2);
//        System.out.println(repo.getAllCars());
//        var repoFilename = "repo.json";
//        var repoJsonSerializer = new CarRepositoryJsonSerializer(repoGsonConverter);
//        repoJsonSerializer.toJson(repo, repoFilename);

        var deserializer = context.getBean("carRepositoryJsonDeserializer", CarRepositoryJsonDeserializer.class);
        var repo2 = deserializer.fromJson("repo.json");
        System.out.println(repo2.getAllCars());





    }
}
