package com.lab.app;

import java.util.*;

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
        int userChoice;
        ArrayList<Engine> engines = new ArrayList<>();
        MenuItems menuItem;

        do {
            Menu.printMenu();
            System.out.print("┃ Введите номер пункта: ");
            userChoice = Inputers.checkInt(1, 5);
            menuItem = MenuItems.values()[userChoice - 1];

            switch (menuItem) {
                case ADD_NEW_ENGINE -> OperationsWithEngines.addEngine(engines);
                case DELETE_ENGINE ->
                        OperationsWithEngines.deleteEngine(engines);
                case PRINT_ENGINES ->
                        OperationsWithEngines.printEngines(engines);
                case COMPARE_ENGINES ->
                        OperationsWithEngines.compareEngines(engines);
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
    }
}
