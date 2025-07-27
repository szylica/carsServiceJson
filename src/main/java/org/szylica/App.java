package org.szylica;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.szylica.config.AppBeansConfig;
import org.szylica.converter.car.impl.JsonFileToCarsConverterImpl;
import org.szylica.data.json.deserializer.impl.CarRepositoryJsonDeserializer;
import org.szylica.data.json.deserializer.impl.CarsDataJsonDeserializer;
import org.szylica.data.json.serializer.impl.CarsDataJsonSerializer;
import org.szylica.model.car.Car;
import org.szylica.model.car.CarComparator;
import org.szylica.model.car.CarMapper;
import org.szylica.model.color.Color;
import org.szylica.repository.impl.CarsRepositoryImpl;
import org.szylica.service.impl.CarsServiceImpl;
import org.szylica.validator.impl.CarDataValidator;

import java.math.BigDecimal;
import java.util.List;

public class App
{
    public static void main( String[] args )
    {

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

        var filename = "src/main/resources/cars.json";

        var carsJsonDeserializer = context.getBean("carsDataJsonDeserializer", CarsDataJsonDeserializer.class);

        //carsJsonDeserializer.fromJson(filename).cars().forEach(System.out::println);

//        var carJsonSerializer = context.getBean("carJsonSerializer", CarsDataJsonSerializer.class);
//        carJsonSerializer.toJson(car1, filename);

//        var repo = new CarsRepositoryImpl();
//        repo.addCar(car1);
//        repo.addCar(car2);
//        System.out.println(repo.getAllCars());
//        var repoFilename = "repo.json";
//        var repoJsonSerializer = new CarRepositoryJsonSerializer(repoGsonConverter);
//        repoJsonSerializer.toJson(repo, repoFilename);

//        var deserializer = context.getBean("carRepositoryJsonDeserializer", CarRepositoryJsonDeserializer.class);
//        var repo2 = deserializer.fromJson("java/resources/repo.json");
//        System.out.println(repo2.getAllCars());


        var carValidator = context.getBean("carDataValidator", CarDataValidator.class);

        var carRepo = new CarsRepositoryImpl();
        carRepo.addAllCars(new JsonFileToCarsConverterImpl(carsJsonDeserializer, carValidator).convert("src/main/resources/cars.json"));

        var service = new CarsServiceImpl(carRepo);

        //System.out.println(service.groupAndFindMinMaxByCriteria(CarMapper.carToBrand, CarComparator.priceCarComparator));
        //System.out.println(service.getPriceAndSpeedStatistics());




    }
}
