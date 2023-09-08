package com.lab.app;

import java.util.Objects;

/**
 * Производный класс, содержащий информацию о дизельном двигателе
 */
public class DieselEngine extends Engine {

    /**
     * Производитель двигателя
     */
    private String engineManufacturer;

    /**
     * Рабочий объём двигателя
     */
    private double engineDisplacement;

    /**
     * Конструктор по-умолчанию.
     */
    public DieselEngine() {
        engineManufacturer = "default";
        engineDisplacement = 0.0;
    }

    /**
     * Конструктор с параметрами.
     * @param engineManufacturer Производитель двигателя.
     * @param engineDisplacement Рабочий объём двигателя
     * @param engineName Название двигателя
     * @param enginePower Мощность двигателя
     */
    public DieselEngine(String engineManufacturer, double engineDisplacement,
                        String engineName, double enginePower) {
        super(engineName, enginePower);

        this.engineManufacturer = engineManufacturer;
        this.engineDisplacement = engineDisplacement;
    }

    /**
     * Устанавливает значение переменной.
     * @param engineManufacturer Значение переменной.
     */
    public void setEngineManufacturer(String engineManufacturer) {
        if (!engineManufacturer.isEmpty()) {
            this.engineManufacturer = engineManufacturer;
        }
    }

    /**
     * Устанавливает значение переменной.
     * @param engineManufacturer Значение переменной.
     */
    public void setEngineDisplacement(double engineDisplacement) {
        if (engineDisplacement != 0) {
            this.engineDisplacement = engineDisplacement;
        }
    }

    /**
     * Сравнение объектов
     * @param o Объект для сравнения.
     * @return Результат сравнения объектов.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DieselEngine dieselEngine = (DieselEngine) o;
        return Double.compare(dieselEngine.engineDisplacement, engineDisplacement) == 0 &&
                Objects.equals(engineManufacturer, dieselEngine.engineManufacturer);
    }

    /**
     * Возвращает числовое значение фиксированной длины для любого объекта.
     * @return Числовое значение фиксированной длины для любого объекта.
     */
    @Override
    public int hashCode() {
        return Objects.hash(engineManufacturer, engineDisplacement);
    }


    /**
     * Вывод информации об объекте.
     * @return Информация объекта.
     */
    @Override
    public String toString() {
        return "┃ Дизельный двигатель" + "\n" +
                "┃ Название двигателя: " + getEngineName() + "\n" +
                "┃ Мощность двигателя: " + getEnginePower() + "\n" +
                "┃ Производитель двигателя: " + engineManufacturer + "\n" +
                "┃ Рабочий объём двигателя: " + engineDisplacement + "\n";
    }
}
