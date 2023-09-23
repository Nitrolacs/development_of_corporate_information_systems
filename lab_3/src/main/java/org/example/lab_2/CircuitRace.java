package org.example.lab_2;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * Класс кольцевой гонки
 */
public class CircuitRace implements Race{

    /**
     * Init метод
     */
    @PostConstruct
    public void doMyInit() {
        System.out.println("Произвожу инициализацию CircuitRace");
    }

    /**
     * Destroy метод
     */
    @PreDestroy
    public void doMyDestroy() {
        System.out.println("Произвожу 'уничтожение' CircuitRace");
    }

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
        System.out.println("В кольцевой гонке " + numberOfLaps + " кругов");
    }
}
