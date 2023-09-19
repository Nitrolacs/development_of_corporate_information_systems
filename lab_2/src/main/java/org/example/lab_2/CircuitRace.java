package org.example.lab_2;

/**
 * Класс кольцевой гонки
 */
public class CircuitRace implements Race{

    /**
     * Количество кругов в гонке.
     */
    private final int numberOfLaps;

    /**
     * Конструктор класса
     * @param numberOfLaps Количество кругов в гонке.
     */
    public CircuitRace(int numberOfLaps) {
        this.numberOfLaps = numberOfLaps;
    }

    /**
     * Вывод информации о гонке.
     */
    @Override
    public void showInformation() {
        System.out.println("В кольцевой гонке " + numberOfLaps + "кругов");
    }
}
