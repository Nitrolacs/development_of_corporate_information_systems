package src.main.java;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String userChoice;
        ArrayList<Engine> engines = new ArrayList<>();

        do {
            Menu.printMenu();
            System.out.print("┃ Введите номер пункта: ");
            userChoice = scanner.nextLine();

            switch (userChoice) {
                case "1" -> OperationsWithEngines.addEngine(engines);
                case "2" -> OperationsWithEngines.deleteEngine(engines);
                case "3" -> OperationsWithEngines.printEngines(engines);
                case "4" -> OperationsWithEngines.compareEngines(engines);
                case "5" -> System.out.print("""
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
        } while (!userChoice.equals("5"));
    }
}
