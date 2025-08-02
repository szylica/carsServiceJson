package org.repository.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.szylica.converter.car.FileToCarsConverter;
import org.szylica.repository.CarsRepository;
import org.szylica.repository.impl.CarsRepositoryImpl;
import org.szylica.service.ExampleData;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class CarRepositoryImplGetAllCarsTest {

    @Mock
    private FileToCarsConverter fileToCarsConverter;

    private CarsRepositoryImpl carsRepository;


    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);

        var cars = ExampleData.allCars;
        var FILENAME = "cars.json";
        Mockito.when(fileToCarsConverter.convert(FILENAME)).thenReturn(cars);
        carsRepository.filename =;
        carsRepository.init();
    }

    @Test
    @DisplayName("")
    void test1(){

    }

}
