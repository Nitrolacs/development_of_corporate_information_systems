package org.example.lab_4;

import java.util.*;

/**
 * Класс, содержащий методы для проверки введённых данных.
 */
public class Inputers {

    /**
     * Проверяет, что ввод - целое число.
     * @param firstNumber Нижний диапазон.
     * @param secondNumber Верхний диапазон.
     * @return userInt Целое число.
     */
    public static int checkInt(int firstNumber, int secondNumber) {
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        int userInt = 0;
        boolean correctInput = false;

        do {
            try {
                userInt = Integer.parseInt(userInput);
                if (userInt < firstNumber || userInt > secondNumber) {
                    System.out.print("┃ Число за пределами диапазона! Введите число заново: ");
                    userInput = scanner.nextLine();
                } else {
                    correctInput = true;
                }
            } catch (NumberFormatException e) {
                System.out.print("┃ Введено не целое число! Введите число заново: ");
                userInput = scanner.nextLine();
            }
        } while (!correctInput);

        return userInt;
    }

    /**
     * Проверяет получение строки от ввода пользователя.
     * @return Введенная строка.
     */
    public static String checkString() {
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();

        while (userInput.isEmpty()) {
            System.out.print("┃ Пустой ввод. Введите строку: ");
            userInput = scanner.nextLine();
        }
        return userInput;
    }

    /**
     * Проверяет, что ввод - вещественное число.
     * @param firstNumber Нижний диапазон.
     * @param secondNumber Верхний диапазон.
     * @return userDouble Вещественное число.
     */
    public static double checkDouble(double firstNumber, double secondNumber) {
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
}
