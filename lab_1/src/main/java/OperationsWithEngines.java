package src.main.java;

import java.util.*;

public class OperationsWithEngines {

    public static Engine createNewEngine(int constructorUserChoice, String engineName,
                                          double enginePower) {
        Menu.printMessage(2); // TODO: Bad practice, need to fix it

        if (constructorUserChoice == 1) { // TODO: убрать магическое число
            return new Engine();
        } else if (constructorUserChoice == 2) {
            return new Engine(engineName, enginePower);
        } else {
            Menu.printMessage(3); // TODO: убрать магическое число
        }
        return new Engine();
    }

    public static InternalCombustionEngine createNewICE(int constructorUserChoice,
                                                         String engineName,
                                                         double enginePower) {
        if (constructorUserChoice == 1) {
            Menu.printMessage(2);
            return new InternalCombustionEngine();
        } else if (constructorUserChoice == 2) {
            System.out.print("┃ Введите тип двигателя: ");
            String engineType = Inputers.checkString();

            System.out.print("┃ Введите крутящий момент двигателя (целое число): ");
            int engineTorque = Inputers.checkInt(1, Integer.MAX_VALUE);

            Menu.printMessage(2);
            return new InternalCombustionEngine(engineType, engineTorque,
                    engineName, enginePower);
        } else {
            Menu.printMessage(3);
        }
        return new InternalCombustionEngine(); // TODO: Проверить, нужен ли здесь еще один возврат объекта
    }

    public static DieselEngine createNewDieselEngine(int constructorUserChoice,
                                                      String engineName,
                                                      double enginePower) {
        if (constructorUserChoice == 1) {
            Menu.printMessage(2);
            return new DieselEngine();
        } else if (constructorUserChoice == 2) {
            System.out.print("┃ Введите производителя двигателя: ");
            String engineManufacturer = Inputers.checkString();

            System.out.print("┃ Введите рабочий объём двигателя (вещественное число): ");
            double engineDisplacement = Inputers.checkDouble(Double.MIN_VALUE, Double.MAX_VALUE);

            Menu.printMessage(2);
            return new DieselEngine(engineManufacturer, engineDisplacement,
                    engineName, enginePower);
        } else {
            Menu.printMessage(3);
        }
        return new DieselEngine();
    }

    // TODO: Попробовать применить шаблон фабрика!!!!!
    public static JetEngine createNewJetEngine(int constructorUserChoice,
                                                String engineName,
                                                double enginePower) {
        if (constructorUserChoice == 1) {
            Menu.printMessage(2);
            return new JetEngine();
        } else if (constructorUserChoice == 2) {
            System.out.print("┃ Введите тип топлива: ");
            String engineFuel = Inputers.checkString();

            System.out.print("┃ Введите удельный импульс двигателя (вещественное число): ");
            double engineSpecificImpulse = Inputers.checkDouble(Double.MIN_VALUE,
                    Double.MAX_VALUE);

            Menu.printMessage(2);
            return new JetEngine(engineFuel, engineSpecificImpulse,
                    engineName, enginePower);
        } else {
            Menu.printMessage(3);
        }
        return new JetEngine();
    }

    public static void addEngine(ArrayList<Engine> engines) {
        String message = "┃ Введите номер пункта: ";
        String engineName = "";
        double enginePower = 0.0;

        Menu.printMenuChoiceType();
        System.out.print(message);
        int engineUserChoice = Inputers.checkInt(1, 4); // TODO: Избавиться от маг. чисел

        Menu.printMenuChoiceConstructor();
        System.out.print(message);
        int constructorUserChoice = Inputers.checkInt(1, 2); // TODO: Избавиться от маг. чисел

        if (constructorUserChoice != 1) {
            System.out.print("┃ Введите название двигателя: ");
            engineName = Inputers.checkString();

            System.out.print("┃ Введите мощность двигателя (вещественное число): ");
            enginePower = Inputers.checkDouble(0.0, Double.MAX_VALUE);
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

            default -> Menu.printMessage(3);
        }
    }

    public static void printEngines(ArrayList<Engine> engines) {
        int count = 1;

        if (engines.isEmpty()) {
            Menu.printMessage(1);
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

    public static void deleteEngine(ArrayList<Engine> engines) {
        printEngines(engines);

        if (!engines.isEmpty()) {
            System.out.print("┃ Введите номер двигателя для удаления: ");
            int numberOfEngine = Inputers.checkInt(1, engines.size());
            engines.remove(numberOfEngine - 1);
            System.out.println("┃ Двигатель №" + numberOfEngine + " удалён.");
        }
    }

    public static void compareEngines(ArrayList<Engine> engines) {
        if (engines.isEmpty() || engines.size() < 2) {
            Menu.printMessage(1);
        } else {
            printEngines(engines);

            System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
            System.out.print("┃ Введите номер первого двигателя для сравнения: ");
            int firstEngineIndex = Inputers.checkInt(1, engines.size()) - 1;

            System.out.print("┃ Введите номер второго двигателя для сравнения: ");
            int secondEngineIndex = Inputers.checkInt(1, engines.size()) - 1;

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
}