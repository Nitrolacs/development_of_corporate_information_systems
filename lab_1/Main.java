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
                } else {
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
        } else if (constructorUserChoice == 2) {
            return new Engine(engineName, enginePower);
        } else {
            printMessage(3); // TODO: убрать магическое число
        }
        return new Engine();
    }

    private static InternalCombustionEngine createNewICE(int constructorUserChoice,
                                                         String engineName,
                                                         double enginePower) {
        if (constructorUserChoice == 1) {
            printMessage(2);
            return new InternalCombustionEngine();
        } else if (constructorUserChoice == 2) {
            System.out.print("┃ Введите тип двигателя: ");
            String engineType = checkString();

            System.out.print("┃ Введите крутящий момент двигателя (целое число): ");
            int engineTorque = checkInt(1, Integer.MAX_VALUE);

            printMessage(2);
            return new InternalCombustionEngine(engineType, engineTorque,
                    engineName, enginePower);
        } else {
            printMessage(3);
        }
        return new InternalCombustionEngine(); // TODO: Проверить, нужен ли здесь еще один возврат объекта
    }

    private static DieselEngine createNewDieselEngine(int constructorUserChoice,
                                                      String engineName,
                                                      double enginePower) {
        if (constructorUserChoice == 1) {
            printMessage(2);
            return new DieselEngine();
        } else if (constructorUserChoice == 2) {
            System.out.print("┃ Введите производителя двигателя: ");
            String engineManufacturer = checkString();

            System.out.print("┃ Введите рабочий объём двигателя (вещественное число): ");
            double engineDisplacement = checkDouble(Double.MIN_VALUE, Double.MAX_VALUE);

            printMessage(2);
            return new DieselEngine(engineManufacturer, engineDisplacement,
                    engineName, enginePower);
        } else {
            printMessage(3);
        }
        return new DieselEngine();
    }

    // TODO: Попробовать применить шаблон фабрика!!!!!
    private static JetEngine createNewJetEngine(int constructorUserChoice,
                                                String engineName,
                                                double enginePower) {
        if (constructorUserChoice == 1) {
            printMessage(2);
            return new JetEngine();
        } else if (constructorUserChoice == 2) {
            System.out.print("┃ Введите тип топлива: ");
            String engineFuel = checkString();

            System.out.print("┃ Введите удельный импульс двигателя (вещественное число): ");
            double engineSpecificImpulse = checkDouble(Double.MIN_VALUE,
                    Double.MAX_VALUE);

            printMessage(2);
            return new JetEngine(engineFuel, engineSpecificImpulse,
                    engineName, enginePower);
        } else {
            printMessage(3);
        }
        return new JetEngine();
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

            case 1 ->
                    engines.add(createNewEngine(constructorUserChoice, engineName,
                            enginePower));

            case 2 ->
                    engines.add(createNewICE(constructorUserChoice, engineName,
                            enginePower));

            case 3 ->
                    engines.add(createNewDieselEngine(constructorUserChoice, engineName,
                            enginePower));

            case 4 ->
                    engines.add(createNewJetEngine(constructorUserChoice, engineName,
                            enginePower));

            default -> printMessage(3);
        }
    }

    private static void printEngines(ArrayList<Engine> engines) {
        int count = 1;

        if (engines.isEmpty()) {
            printMessage(1);
        } else {
            for (Engine e : engines) {
                System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
                System.out.println("┃                    Двигатель " + "№" + count + "                     ┃");
                System.out.println("┣━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
                System.out.print(e.toString());
                count++;
            }
        }
    }

    private static void deleteEngine(ArrayList<Engine> engines) {
        printEngines(engines);

        if (!engines.isEmpty()) {
            System.out.print("┃ Введите номер двигателя для удаления: ");
            int numberOfEngine = checkInt(1, engines.size());
            engines.remove(numberOfEngine - 1);
            System.out.println("┃ Двигатель №" + numberOfEngine + " удалён.");
        }
    }

    private static void compareEngines(ArrayList<Engine> engines) {
        if (engines.isEmpty() || engines.size() < 2) {
            printMessage(1);
        } else {
            printEngines(engines);

            System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
            System.out.print("┃ Введите номер первого двигателя для сравнения: ");
            int firstEngineIndex = checkInt(1, engines.size()) - 1;

            System.out.print("┃ Введите номер второго двигателя для сравнения: ");
            int secondEngineIndex = checkInt(1, engines.size()) - 1;

            Engine firstEngine = engines.get(firstEngineIndex);
            Engine secondEngine = engines.get(secondEngineIndex);

            if (!(secondEngineIndex == firstEngineIndex)) {
                if (firstEngine.hashCode() ==
                        secondEngine.hashCode()) {
                    if (firstEngine.equals(secondEngine)) {
                        System.out.println("┃ Двигатели равны.");
                    }
                    else {
                        System.out.println("┃ Двигатели не равны.");
                    }
                }

                else {
                    System.out.println("┃ Хэши двух двигателей не равны." +
                            " Двигатели не равны");
                }
            }

            else {
                System.out.println("┃ Двигатели равны (одинаковые номера).");
            }
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
                case "1" -> addEngine(engines);
                case "2" -> deleteEngine(engines);
                case "3" -> printEngines(engines);
                case "4" -> compareEngines(engines);
                case "5" -> System.out.print("""
                        ┏━━━━━━━━━━━━━━━━━━━━━━━━━┓
                        ┃ Завершение программы... ┃
                        ┗━━━━━━━━━━━━━━━━━━━━━━━━━┛
                        """);
                default -> {
                }
            }
        } while (!userChoice.equals("5"));
    }
}
