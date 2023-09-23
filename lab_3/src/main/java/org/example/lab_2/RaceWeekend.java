package org.example.lab_2;

import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.beans.ConstructorProperties;

public class RaceWeekend {

    /**
     * Выбранная гонка
     */
    private final Race race;

    /**
     * Название гоночного уикенда
     */
    private final String name;

    /**
     * Длительность гонки в часах
     */
    private int durationInHours;

    /**
     * Init метод
     */
    @PostConstruct
    public void doMyInit() {
        System.out.println("Произвожу инициализацию RaceWeekend");
    }

    /**
     * Destroy метод
     */
    @PreDestroy
    public void doMyDestroy() {
        System.out.println("Произвожу 'уничтожение' RaceWeekend");
    }

    /**
     * Конструктор класса
     * @param race Выбранная гонка
     * @param name Название гоночного уикенда
     */
    public RaceWeekend(Race race, String name) {
        this.race = race;
        this.name = name;
    }

    /**
     * Сеттер поля durationInHours
     * @param durationInHours Длительность гонки в часах
     */
    @Value("${f1Weekend.durationInHours}")
    public void setDurationInHours(int durationInHours) {
        this.durationInHours = durationInHours;
    }

    /**
     * Метод старта гоночного уикенда.
     */
    public void startWeekend() {
        System.out.println("Гоночный уикенд " + name + " начался! Он " +
                "продлится " + durationInHours + " часов.");
        System.out.println("Информация о гонке:");
        race.showInformation();
        System.out.println();
    }
}
