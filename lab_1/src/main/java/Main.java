package src.main.java;

import java.util.*;

enum MenuItems {
	ADD_NEW_ENGINE,
	DELETE_ENGINE,
	PRINT_ENGINES,
	COMPARE_ENGINES,
	EXIT_PROGRAM
}

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
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
                case DELETE_ENGINE -> OperationsWithEngines.deleteEngine(engines);
                case PRINT_ENGINES -> OperationsWithEngines.printEngines(engines);
                case COMPARE_ENGINES -> OperationsWithEngines.compareEngines(engines);
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
