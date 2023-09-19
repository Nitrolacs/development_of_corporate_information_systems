package org.example.lab_2;

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
     * Конструктор класса
     * @param race Выбранная гонка
     * @param name Название гоночного уикенда
     */
    @ConstructorProperties({"race", "name"})
    public RaceWeekend(Race race, String name) {
        this.race = race;
        this.name = name;
    }

    /**
     * Сеттер поля durationInHours
     * @param durationInHours Длительность гонки в часах
     */
    public void setDurationInHours(int durationInHours) {
        this.durationInHours = durationInHours;
    }

    /**
     * Метод старта гоночного уикенда.
     */
    public void startWeekend() {
        System.out.println("Гоночный уикенд " + race + " начался! Он " +
                "продлится " + durationInHours + " часов.");
        System.out.println("Информация о гонке:");
        race.showInformation();
    }
}
