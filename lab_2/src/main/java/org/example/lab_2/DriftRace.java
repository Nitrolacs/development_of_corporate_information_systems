package org.example.lab_2;

/**
 * Класс дрифт гонки.
 */
public class DriftRace implements Race{

    /**
     * Количество гонщиков, которые принимают участие.
     */
    private final int numberOfRacers;

    /**
     * Конструктор класса
     * @param numberOfRacers Количество гонщиков, которые принимают участие.
     */
    public DriftRace(int numberOfRacers) {
        this.numberOfRacers = numberOfRacers;
    }

    /**
     * Вывод информации о гонке.
     */
    @Override
    public void showInformation() {
        System.out.println("Количество участников в дрифте: " + numberOfRacers);
    }
}
