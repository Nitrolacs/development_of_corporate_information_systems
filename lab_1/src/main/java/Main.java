package src.main.java;

import src.main.java.engines.Engine;
import src.main.java.enums.MenuItems;
import src.main.java.functionality.Inputers;
import src.main.java.functionality.Menu;
import src.main.java.functionality.OperationsWithEngines;

import java.util.*;

public class Main {
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
