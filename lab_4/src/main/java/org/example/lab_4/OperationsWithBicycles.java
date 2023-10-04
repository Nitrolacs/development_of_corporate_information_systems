package org.example.lab_4;

import java.util.List;

public class OperationsWithBicycles {

    public static void insertNewBike(BikeDao bikeDao) {
        String message = "┃ Введите номер пункта: ";

        Menu.printMenuChoiceConstructor();
        System.out.print(message);
        int constructorUserChoice = Inputers.checkInt(1, 2);

        if (constructorUserChoice == 1) {
            bikeDao.insert(new Bike());
        } else {
            System.out.print("┃ Введите цену велосипеда: ");
            Double bikePrice = Inputers.checkDouble(0.0, Double.MAX_VALUE);

            System.out.print("┃ Введите количество скоростей велосипеда: ");
            Integer bikeNumberOfSpeeds = Inputers.checkInt(0, Integer.MAX_VALUE);

            System.out.print("┃ Введите название велосипеда: ");
            String bikeName = Inputers.checkString();

            System.out.print("┃ Введите тип велосипеда: ");
            String bikeType = Inputers.checkString();

            System.out.print("┃ Введите материал рамы велосипеда: ");
            String bikeFrameMaterial = Inputers.checkString();

            bikeDao.insert(new Bike(null, bikePrice, bikeNumberOfSpeeds,
                    bikeName, bikeType, bikeFrameMaterial));
        }

        Menu.printMessage(MessageChoices.BIKE_IS_ADDED);
    }

    public static void printAllBicycles(BikeDao bikeDao) {
        List<Bike> bicycles = bikeDao.findAll();

        if (bicycles.isEmpty()) {
            Menu.printMessage(MessageChoices.BICYCLES_ARE_NOT_ADDED);
        } else {
            for (Bike bike : bicycles) {
                System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
                System.out.println("┃                       Велосипед                     ┃");
                System.out.println("┣━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
                System.out.println(bike.toString());
            }
        }
    }
}
