package com.lab.app;

import java.util.Objects;

/**
 * Базовый класс, содержащий информацию о двигателе
 */
public class Engine {

    /**
     * Название двигателя
     */
    private String engineName;

    /**
     * Мощность двигателя
     */
    private double enginePower;

    /**
     * Конструктор по-умолчанию
     */
    public Engine() {
        engineName = "default";
        enginePower = 0.0;
    }

    /**
     * Конструктор с параметрами.
     * @param engineName Название двигателя.
     * @param enginePower Мощность двигателя
     */
    public Engine(String engineName, double enginePower) {
        this.engineName = engineName;
        this.enginePower = enginePower;
    }

    /**
     * Возвращает название двигателя
     * @return Название двигателя
     */
    public String getEngineName() {
        return engineName;
    }

    /**
     * Возвращает мощность двигателя
     * @return Мощность двигателя
     */
    public double getEnginePower() {
        return enginePower;
    }

    /**
     * Устанавливает название двигателя
     * @param engineName Название двигателя
     */
    public void setEngineName(String engineName) {
        if (!engineName.isEmpty()) {
            this.engineName = engineName;
        }
    }

    /**
     * Устанавливает мощность двигателя
     * @param enginePower Мощность двигателя
     */
    public void setEnginePower(double enginePower) {
        if (enginePower != 0.0) {
            this.enginePower = enginePower;
        }
    }

    /**
     * Сравнение объектов
     * @param o объект для сравнения.
     * @return результат сравнения объектов.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Engine engine = (Engine) o;
        return Double.compare(engine.enginePower, enginePower) == 0 &&
                Objects.equals(engineName, engine.engineName);
    }

    /**
     * Возвращает числовое значение фиксированной длины для любого объекта.
     * @return Числовое значение фиксированной длины для любого объекта.
     */
    @Override
    public int hashCode() {
        return Objects.hash(engineName, enginePower);
    }

    /**
     * Вывод информации об объекте.
     * @return Информация объекта.
     */
    @Override
    public String toString() {
        return "┃ Двигатель" + "\n" +
               "┃ Название двигателя: " + engineName + "\n" +
               "┃ Мощность двигателя: " + enginePower + "\n";
    }

}
