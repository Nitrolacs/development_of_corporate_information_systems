package src.main.java.engines;

import java.util.Objects;

/**
 * Производный класс, содержащий информацию о реактивном двигателе
 */
public class JetEngine extends Engine {

    /**
     * Топливо двигателя
     */
    private String engineFuel;

    /**
     * Удельный импульс двигателя
     */
    private double engineSpecificImpulse;  // удельный импульс

    /**
     * Конструктор по-умолчанию
     */
    public JetEngine() {
        engineFuel = "default";
        engineSpecificImpulse = 0.0;
    }

    /**
     * Конструктор с параметрами.
     * @param engineFuel Топливо двигателя
     * @param engineSpecificImpulse Удельный импульс двигателя
     * @param engineName Название двигателя
     * @param enginePower Мощность двигателя
     */
    public JetEngine(String engineFuel, double engineSpecificImpulse, String engineName,
                     double enginePower) {
        super(engineName, enginePower);

        this.engineFuel = engineFuel;
        this.engineSpecificImpulse = engineSpecificImpulse;
    }

    /**
     * Устанавливает топливо двигателя
     * @param engineFuel Топливо двигателя
     */
    public void setEngineFuel(String engineFuel) {
        if (!engineFuel.isEmpty()) {
            this.engineFuel = engineFuel;
        }
    }

    /**
     * Устанавливает удельный импульс двигателя
     * @param engineSpecificImpulse Удельный импульс двигателя
     */
    public void setEngineSpecificImpulse(double engineSpecificImpulse) {
        if (engineSpecificImpulse != 0.0) {
            this.engineSpecificImpulse = engineSpecificImpulse;
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
        JetEngine jetEngine = (JetEngine) o;
        return Double.compare(jetEngine.engineSpecificImpulse, engineSpecificImpulse) == 0 &&
                Objects.equals(engineFuel, jetEngine.engineFuel);
    }

    /**
     * Возвращает числовое значение фиксированной длины для любого объекта.
     * @return Числовое значение фиксированной длины для любого объекта.
     */
    @Override
    public int hashCode() {
        return Objects.hash(engineFuel, engineSpecificImpulse);
    }

    /**
     * Вывод информации об объекте.
     * @return Информация объекта.
     */
    @Override
    public String toString() {
        return "┃ Реактивный двигатель" + "\n" +
                "┃ Название двигателя: " + getEngineName() + "\n" +
                "┃ Мощность двигателя: " + getEnginePower() + "\n" +
                "┃ Топливо: " + engineFuel + "\n" +
                "┃ Удельный импульс: " + engineSpecificImpulse + "\n";
    }
}
