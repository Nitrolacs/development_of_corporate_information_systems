package org.example.lab_4;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Основной класс, точка входа в программу.
 */
public class Main {

    /**
     * Основная функция программы.
     * @param args массив аргументов, введённых при запуске через командную
     *             строку
     */
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new
                AnnotationConfigApplicationContext(SpringConfig.class);
        BikeDao bikeDao = context.getBean("bikeDao", BikeDao.class);

        int userChoice;
        MenuItems menuItem;

        do {
            Menu.printMenu();
            System.out.print("┃ Введите номер пункта: ");
            userChoice = Inputers.checkInt(1, 6);
            menuItem = MenuItems.values()[userChoice - 1];

            switch (menuItem) {
                case ADD_NEW_BIKE -> OperationsWithBicycles.insertNewBike(bikeDao);
                case PRINT_ALL_BICYCLES -> OperationsWithBicycles.printAllBicycles(bikeDao);
                //case EDIT_BIKE_BY_ID -> ;
                //case DELETE_BIKE_BY_ID -> ;
                //case SEARCH_BICYCLES_WITH_LOWER_PRICE -> ;
                                case EXIT_PROGRAM -> System.out.print("""
                        ┏━━━━━━━━━━━━━━━━━━━━━━━━━┓
                        ┃ Завершение программы... ┃
                        ┗━━━━━━━━━━━━━━━━━━━━━━━━━┛
                        """);
                default -> System.out.print("""
                        ┏━━━━━━━━━━━━━━━━━━━━┓
                        ┃ Такого пункта нет! ┃
                        ┗━━━━━━━━━━━━━━━━━━━━┛
                        """);
            }
        } while (!(menuItem == MenuItems.EXIT_PROGRAM));

        context.close();
    }
}
