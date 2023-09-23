package org.example.lab_2;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * Класс раллийной гонки
 */
public class RallyRace implements Race {

    /**
     * Init метод
     */
    @PostConstruct
    public void doMyInit() {
        System.out.println("Произвожу инициализацию RallyRace");
    }

    /**
     * Destroy метод
     */
    @PreDestroy
    public void doMyDestroy() {
        System.out.println("Произвожу 'уничтожение' RallyRace");
    }

    /**
     * Название трассы, на которой проходит гонка.
     */
    private final String trackName;

    /**
     * Конструктор класса
     * @param trackName Название трассы, на которой проходит гонка.
     */
    public RallyRace(String trackName) {
        this.trackName = trackName;
    }

    /**
     * Вывод информации о гонке.
     */
    @Override
    public void showInformation() {
        System.out.println("Раллийная гонка происходит на трассе " + trackName);
    }
}
