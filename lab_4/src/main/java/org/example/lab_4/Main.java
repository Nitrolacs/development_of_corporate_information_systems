package org.example.lab_4;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new
                AnnotationConfigApplicationContext(SpringConfig.class);
        BikeDao bikeDao = context.getBean("bikeDao", BikeDao.class);

        Bike bike = new Bike(null, 10000.00, 5, "New cool bike",
                "BMX", "Steel");
        bikeDao.insert(bike);

        List<Bike> bicycles = bikeDao.findAll();
        System.out.println(Arrays.toString(bicycles.toArray()));

        context.close();

    }
}
