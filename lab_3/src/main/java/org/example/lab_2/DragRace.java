package org.example.lab_2;

import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * Класс драг гонки.
 */
public class DragRace implements Race {

    /**
     * Init метод
     */
    @PostConstruct
    public void doMyInit() {
        System.out.println("Произвожу инициализацию DragRace");
    }

    /**
     * Destroy метод
     */
    @PreDestroy
    public void doMyDestroy() {
        System.out.println("Произвожу 'уничтожение' DragRace");
    }

    /**
     * Максимальная мощность двигателя среди участников гонки.
     */
    private final int maxEnginePower;

    /**
     * Конструктор класса
     * @param maxEnginePower Максимальная мощность двигателя среди гонщиков.
     */
    public DragRace(int maxEnginePower) {
        this.maxEnginePower = maxEnginePower;
    }

    /**
     * Выводи информации о гонке.
     */
    @Override
    public void showInformation() {
        System.out.println("Максимальная мощность двигателя среди всех " +
                "участников на дрэг рейсинге: " + maxEnginePower);
    }

    /**
     * Фабричный метод
     * @param maxEnginePower - максмальная мощность двигателя
     * @return DragRace
     */
    public static DragRace createDragRace(int maxEnginePower) {
        System.out.println("Фабричный метод в деле.");
        return new DragRace(maxEnginePower);
    }
}
