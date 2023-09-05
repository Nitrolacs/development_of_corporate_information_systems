import java.util.*;

public class Main {
    private static void printMenu() {
        System.out.println("""
                ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓
                ┃            Меню программы             ┃
                ┣━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┫
                ┃ 1. Добавление нового двигателя        ┃
                ┃ 2. Удаление двигателя                 ┃
                ┃ 3. Вывод всех двигателей              ┃
                ┃ 4. Сравнение двигателей (по индексу)  ┃
                ┃ 5. Завершение работы программы        ┃
                ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛""");
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String userChoice;
        ArrayList<Engine> engines = new ArrayList<>();

        do {
            printMenu();
            System.out.print("┃ Введите номер пункта: ");
            userChoice = scanner.nextLine();

            switch (userChoice) {

                case "1":
                    break;

                case "2":
                    break;

                case "3":
                    break;

                case "4":
                    break;

                case "5":
                    System.out.println("""
                    ┏━━━━━━━━━━━━━━━━━━━━━━━━━┓
                    ┃ Завершение программы... ┃
                    ┗━━━━━━━━━━━━━━━━━━━━━━━━━┛
                    """);
                    break;

                default:
                    break;
            }
        } while (!userChoice.equals("5"));
    }
}
