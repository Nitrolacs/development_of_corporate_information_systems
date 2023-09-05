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

    private static void printMenuChoiceType() {
        System.out.println("""
                ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓
                ┃        Добавление двигателя         ┃
                ┣━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┫
                ┃ 1. Добавление двигателя             ┃
                ┃ 2. Добавление ДВС                   ┃
                ┃ 3. Добавление дизельного двигателя  ┃
                ┃ 4. Добавление реактивного двигателя ┃
                ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛""");
    }

    private static int checkInt(int firstNumber, int secondNumber) {
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        int userInt = 0;
        boolean correctInput = false;

        do {
            try {
                userInt = Integer.parseInt(userInput);
                if (userInt < firstNumber || userInt > secondNumber) {
                    System.out.println("┃ Число за пределами диапазона! Введите число заново: ");
                    userInput = scanner.nextLine();
                } else {
                    correctInput = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("┃ Введено не целое число! Введите число заново: ");
                userInput = scanner.nextLine();
            }
        } while (!correctInput);

        return userInt;
    }

    private static String checkString() {
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();

        while (userInput.isEmpty()) {
            System.out.println("┃ Пустой ввод. Введите строку: ");
            userInput = scanner.nextLine();
        }
        return userInput;
    }

    // TODO: Объедини checkInt и checkDouble, чтобы избежать повторений
    private static double checkDouble(double firstNumber, double secondNumber) {
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        double userDouble = 0.0;
        boolean correctInput = false;

        do {
            try {
                userDouble = Double.parseDouble(userInput);
                if (userDouble < firstNumber || userDouble > secondNumber) {
                    System.out.print("┃ Число за пределами диапазона! Введите число заново: ");
                    userInput = scanner.nextLine();
                }
                else {
                    correctInput = true;
                }
            } catch (NumberFormatException ex) {
                System.out.print("┃ Введено не целое/десятичное число! Введите число заново: ");
                userInput = scanner.nextLine();
            }
        } while (!correctInput);

        return userDouble;
    }

    private static void printMenuChoiceConstructor() {
        System.out.println("""
                ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓
                ┃ 1. Добавление без параметров ┃
                ┃ 2. Добавление с параметрами  ┃
                ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛""");
    }

    private static void printMessage(int choice) {
        if (choice == 1) {
            System.out.println("""
                    ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓
                    ┃      Двигатели ещё не добавлены      ┃
                    ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛""");
        }

        if (choice == 2) {
            System.out.println("""
                    ┏━━━━━━━━━━━━━━━━━━━━━━┓
                    ┃  Двигатель добавлен  ┃
                    ┗━━━━━━━━━━━━━━━━━━━━━━┛""");
        }

        if (choice == 3) {
            System.out.println("""
                    ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓
                    ┃  Такого пункта нет в меню.  ┃
                    ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛""");
        }
    }

    private static Engine createNewEngine(int constructorUserChoice, String engineName,
                                          double enginePower) {
        printMessage(2); // TODO: Bad practice, need to fix it

        if (constructorUserChoice == 1) { // TODO: убрать магическое число
            return new Engine();
        }
        else if (constructorUserChoice == 2) {
            return new Engine(engineName, enginePower);
        }
        else {
            printMessage(3); // TODO: убрать магическое число
        }
        return new Engine();
    }

    private static void addEngine(ArrayList<Engine> engines) {
        String message = "┃ Введите номер пункта: ";
        String engineName = "";
        double enginePower = 0.0;

        printMenuChoiceType();
        System.out.print(message);
        int engineUserChoice = checkInt(1, 4); // TODO: Избавиться от маг. чисел

        printMenuChoiceConstructor();
        System.out.print(message);
        int constructorUserChoice = checkInt(1, 2); // TODO: Избавиться от маг. чисел

        if (constructorUserChoice != 1) {
            System.out.print("┃ Введите название двигателя: ");
            engineName = checkString();

            System.out.print("┃ Введите мощность двигателя (вещественное число): ");
            enginePower = checkDouble(0.0, Double.MAX_VALUE);
        }

        switch (engineUserChoice) {
            case 1:
                engines.add(createNewEngine(constructorUserChoice, engineName,
                        enginePower));
        }
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
                    addEngine(engines);
                    break;

                case "2":
                    break;

                case "3":
                    break;

                case "4":
                    break;

                case "5":
                    System.out.print("""
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
