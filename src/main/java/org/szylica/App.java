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
import org.szylica.service.impl.CarsServiceImpl;

import java.math.BigDecimal;
import java.util.List;

public class App 
{
    public static void main( String[] args )
    {

        List<String> setOfComponents1 = List.of("BREAKS", "WHEELS");
        List<String> setOfComponents2 = List.of("STEERING WHEEL", "BREAKS");
        List<String> setOfComponents3 = List.of("RADIO", "WHEELS");
        List<String> setOfComponents4 = List.of("AC");
        List<String> setOfComponents5 = List.of("ROOF", "AC");
        List<String> setOfComponents6 = List.of("CUP HOLDER", "HEATED SEATS");

        Car car1 = new Car("BMW", "X1", 200, Color.BLACK, BigDecimal.valueOf(2000), setOfComponents1);

        Car car2 = new Car("BMW", "X2", 190, Color.RED, BigDecimal.valueOf(2100), setOfComponents2);

        Car car3 = new Car("BMW", "X3", 210, Color.GREEN, BigDecimal.valueOf(2200), setOfComponents3);

        Car car4 = new Car("AUDI", "RS1", 200, Color.BLACK, BigDecimal.valueOf(2000), setOfComponents4);

        Car car5 = new Car("AUDI", "RS5", 220, Color.WHITE, BigDecimal.valueOf(2500), setOfComponents5);

        Car car6 = new Car("MERCEDES", "S2", 180, Color.BLACK, BigDecimal.valueOf(1900), setOfComponents6);

        Car car7 = new Car("MERCEDES", "S3", 270, Color.BLACK, BigDecimal.valueOf(2000), setOfComponents2);

        List<Car> allCars = List.of(car1, car2, car3, car4, car5, car6, car7);

//        Car car1 = Car.builder()
//                .brand("BMW")
//                .model("X1")
//                .price(BigDecimal.valueOf(200))
//                .color(Color.WHITE)
//                .speed(200)
//                .components(List.of("BREAKS"))
//                .build();
//
//        Car car2 = Car.builder()
//                .brand("BMW")
//                .model("X2")
//                .price(BigDecimal.valueOf(210))
//                .color(Color.BLACK)
//                .speed(220)
//                .components(List.of("WATER"))
//                .build();

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





        var carRepo = new CarsRepositoryImpl();
        carRepo.addAllCars(allCars);

        var service = new CarsServiceImpl(carRepo);

        System.out.println(service.getPriceAndSpeedStatistics());




    }
}
