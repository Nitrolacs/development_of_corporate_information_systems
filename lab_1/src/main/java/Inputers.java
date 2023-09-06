package src.main.java;

import java.util.*;

public class Inputers {
    public static int checkInt(int firstNumber, int secondNumber) {
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

   public static String checkString() {
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();

        while (userInput.isEmpty()) {
            System.out.println("┃ Пустой ввод. Введите строку: ");
            userInput = scanner.nextLine();
        }
        return userInput;
    }

    // TODO: Объедини checkInt и checkDouble, чтобы избежать повторений
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
