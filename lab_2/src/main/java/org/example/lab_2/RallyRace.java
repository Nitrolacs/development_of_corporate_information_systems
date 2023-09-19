package org.example.lab_2;

/**
 * Класс раллийной гонки
 */
public class RallyRace implements Race {

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
