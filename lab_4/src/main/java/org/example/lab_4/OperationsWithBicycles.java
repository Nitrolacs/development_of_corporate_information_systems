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

    public static void deleteBike(BikeDao bikeDao) {
        printAllBicycles(bikeDao);

        if (!bikeDao.findAll().isEmpty()) {
            System.out.print("\n┃ Введите id велосипеда для удаления: ");
            int numberOfBike = Inputers.checkInt(1, Integer.MAX_VALUE);

            int result = bikeDao.delete(numberOfBike);

            if (result == 1) {
                System.out.println("┃ Велосипед с id " + numberOfBike + " удалён.");
            } else {
                Menu.printMessage(MessageChoices.BIKE_IS_NOT_EXIST);
            }
        }
    }

    public static void editBike(BikeDao bikeDao) {
        printAllBicycles(bikeDao);

        if (!bikeDao.findAll().isEmpty()) {
            System.out.print("\n┃ Введите id велосипеда для изменения: ");
            int numberOfBike = Inputers.checkInt(1, Integer.MAX_VALUE);

            if (bikeDao.isBikeExists(numberOfBike)) {
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

                bikeDao.update(numberOfBike, new Bike(null, bikePrice, bikeNumberOfSpeeds,
                        bikeName, bikeType, bikeFrameMaterial));

                System.out.print("┃ Данные о велосипеде изменены! ");
            } else {
                Menu.printMessage(MessageChoices.BIKE_IS_NOT_EXIST);
            }
        }
    }
}
