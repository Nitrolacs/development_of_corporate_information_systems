package com.lab.app;

import java.util.*;

/**
 * Класс, содержащий основные методы для работ с двигателями
 */
public class OperationsWithEngines {

    /**
     * Константа для пункта меню (создание объекта без конструктора).
     */
    private static final int WITHOUT_CONSTRUCTOR = 1;

    /**
     * Константа для пункта меню (создание объекта с конструктором).
     */
    private static final int WITH_CONSTRUCTOR = 2;

    /**
     * Перечисление, содержащее выбор двигателей.
     */
    enum EngineChoice {
        NEW_ENGINE,
        NEW_ICE,
        NEW_DIESEL_ENGINE,
        NEW_JET_ENGINE
    }

    /**
     * Создает новый двигатель
     * @param constructorUserChoice Выбор конструктора
     * @param engineName Название двигателя
     * @param enginePower Мощность двигателя
     * @return Созданный двигатель
     */
    public static Engine createNewEngine(int constructorUserChoice,
                                         String engineName,
                                         double enginePower) {
        Menu.printMessage(MessageChoices.ENGINE_IS_ADDED);

        if (constructorUserChoice == WITHOUT_CONSTRUCTOR) {
            return new Engine();
        } else if (constructorUserChoice == WITH_CONSTRUCTOR) {
            return new Engine(engineName, enginePower);
        } else {
            Menu.printMessage(MessageChoices.THIS_ITEM_IS_NOT_ON_THE_MENU);
        }
        return new Engine();
    }

    /**
     * Создаёт новый двс
     * @param constructorUserChoice Выбор конструктора
     * @param engineName Название двигателя
     * @param enginePower Мощность двигателя
     * @return Созданный двигатель
     */
    public static InternalCombustionEngine createNewICE(int constructorUserChoice,
                                                        String engineName,
                                                        double enginePower) {
        if (constructorUserChoice == WITHOUT_CONSTRUCTOR) {
            Menu.printMessage(MessageChoices.ENGINE_IS_ADDED);
            return new InternalCombustionEngine();
        } else if (constructorUserChoice == WITH_CONSTRUCTOR) {
            System.out.print("┃ Введите тип двигателя: ");
            String engineType = Inputers.checkString();

            System.out.print("┃ Введите крутящий момент двигателя (целое число): ");
            int engineTorque = Inputers.checkInt(1, Integer.MAX_VALUE);

            Menu.printMessage(MessageChoices.ENGINE_IS_ADDED);
            return new InternalCombustionEngine(engineType, engineTorque,
                    engineName, enginePower);
        } else {
            Menu.printMessage(MessageChoices.THIS_ITEM_IS_NOT_ON_THE_MENU);
        }
        return new InternalCombustionEngine();
    }

    /**
     * Создаёт новый дизельный двигатель
     * @param constructorUserChoice Выбор конструктора
     * @param engineName Название двигателя
     * @param enginePower Мощность двигателя
     * @return Созданный двигатель
     */
    public static DieselEngine createNewDieselEngine(int constructorUserChoice,
                                                     String engineName,
                                                     double enginePower) {
        if (constructorUserChoice == WITHOUT_CONSTRUCTOR) {
            Menu.printMessage(MessageChoices.ENGINE_IS_ADDED);
            return new DieselEngine();
        } else if (constructorUserChoice == WITH_CONSTRUCTOR) {
            System.out.print("┃ Введите производителя двигателя: ");
            String engineManufacturer = Inputers.checkString();

            System.out.print("┃ Введите рабочий объём двигателя (вещественное число): ");
            double engineDisplacement = Inputers.checkDouble(Double.MIN_VALUE, Double.MAX_VALUE);

            Menu.printMessage(MessageChoices.ENGINE_IS_ADDED);
            return new DieselEngine(engineManufacturer, engineDisplacement,
                    engineName, enginePower);
        } else {
            Menu.printMessage(MessageChoices.THIS_ITEM_IS_NOT_ON_THE_MENU);
        }
        return new DieselEngine();
    }

    /**
     * Создаёт новый реактивный двигатель
     * @param constructorUserChoice Выбор конструктора
     * @param engineName Название двигателя
     * @param enginePower Мощность двигателя
     * @return Созданный двигатель
     */
    public static JetEngine createNewJetEngine(int constructorUserChoice,
                                               String engineName,
                                               double enginePower) {
        if (constructorUserChoice == WITHOUT_CONSTRUCTOR) {
            Menu.printMessage(MessageChoices.ENGINE_IS_ADDED);
            return new JetEngine();
        } else if (constructorUserChoice == WITH_CONSTRUCTOR) {
            System.out.print("┃ Введите тип топлива: ");
            String engineFuel = Inputers.checkString();

            System.out.print("┃ Введите удельный импульс двигателя (вещественное число): ");
            double engineSpecificImpulse = Inputers.checkDouble(Double.MIN_VALUE,
                    Double.MAX_VALUE);

            Menu.printMessage(MessageChoices.ENGINE_IS_ADDED);
            return new JetEngine(engineFuel, engineSpecificImpulse,
                    engineName, enginePower);
        } else {
            Menu.printMessage(MessageChoices.THIS_ITEM_IS_NOT_ON_THE_MENU);
        }
        return new JetEngine();
    }

    /**
     * Добавляет новый двигатель
     * @param engines Коллекция двигателей.
     */
    public static void addEngine(ArrayList<Engine> engines) {
        String message = "┃ Введите номер пункта: ";
        String engineName = "";
        double enginePower = 0.0;

        Menu.printMenuChoiceType();
        System.out.print(message);
        int engineUserChoice = Inputers.checkInt(1, 4);
        EngineChoice engineChoice = EngineChoice.values()[engineUserChoice - 1];

        Menu.printMenuChoiceConstructor();
        System.out.print(message);
        int constructorUserChoice = Inputers.checkInt(1, 2);

        if (constructorUserChoice != 1) {
            System.out.print("┃ Введите название двигателя: ");
            engineName = Inputers.checkString();

            System.out.print("┃ Введите мощность двигателя (вещественное число): ");
            enginePower = Inputers.checkDouble(0.0, Double.MAX_VALUE);
        }

        switch (engineChoice) {

            case NEW_ENGINE ->
                    engines.add(createNewEngine(constructorUserChoice, engineName,
                            enginePower));

            case NEW_ICE ->
                    engines.add(createNewICE(constructorUserChoice, engineName,
                            enginePower));

            case NEW_DIESEL_ENGINE ->
                    engines.add(createNewDieselEngine(constructorUserChoice, engineName,
                            enginePower));

            case NEW_JET_ENGINE ->
                    engines.add(createNewJetEngine(constructorUserChoice, engineName,
                            enginePower));

            default ->
                    Menu.printMessage(MessageChoices.THIS_ITEM_IS_NOT_ON_THE_MENU);
        }
    }

    /**
     * Выводит все двигатели
     * @param engines Коллекция двигателей
     */
    public static void printEngines(ArrayList<Engine> engines) {
        int count = 1;

        if (engines.isEmpty()) {
            Menu.printMessage(MessageChoices.ENGINES_ARE_NOT_ADDED);
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

    /**
     * Удаляет выбранный двигатель
     * @param engines Коллекция двигателей
     */
    public static void deleteEngine(ArrayList<Engine> engines) {
        printEngines(engines);

        if (!engines.isEmpty()) {
            System.out.print("┃ Введите номер двигателя для удаления: ");
            int numberOfEngine = Inputers.checkInt(1, engines.size());
            engines.remove(numberOfEngine - 1);
            System.out.println("┃ Двигатель №" + numberOfEngine + " удалён.");
        }
    }

    /**
     * Сравнивает двигатели (по индексу)
     * @param engines Коллекция двигателей
     */
    public static void compareEngines(ArrayList<Engine> engines) {
        if (engines.isEmpty() || engines.size() < 2) {
            Menu.printMessage(MessageChoices.ENGINES_ARE_NOT_ADDED);
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
                    } else {
                        System.out.println("┃ Двигатели не равны.");
                    }
                } else {
                    System.out.println("┃ Хэши двух двигателей не равны." +
                            " Двигатели не равны");
                }
            } else {
                System.out.println("┃ Двигатели равны (одинаковые номера).");
            }
        }
    }
}