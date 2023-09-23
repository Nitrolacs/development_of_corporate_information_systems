package org.example.lab_3;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * Класс дрифт гонки.
 */
public class DriftRace implements Race{

    /**
     * Init метод
     */
    @PostConstruct
    public void doMyInit() {
        System.out.println("Произвожу инициализацию DriftRace");
    }

    /**
     * Destroy метод
     */
    @PreDestroy
    public void doMyDestroy() {
        System.out.println("Произвожу 'уничтожение' DriftRace");
    }

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
