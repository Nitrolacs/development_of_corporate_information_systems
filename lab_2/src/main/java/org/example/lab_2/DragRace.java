package org.example.lab_2;

/**
 * Класс драг гонки.
 */
public class DragRace implements Race {

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
}
