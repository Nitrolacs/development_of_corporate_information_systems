package src.main.java.engines;

import java.util.Objects;

/**
 * Производный класс, содержащий информацию о двигателе внутреннего сгорания.
 */
public class InternalCombustionEngine extends Engine {

    /**
     * Тип двигателя
     */
    private String engineType;

    /**
     * Крутящий момент двигателя
     */
    private int engineTorque;

    /**
     * Конструктор по умолчанию
     */
    public InternalCombustionEngine() {
        engineType = "default";
        engineTorque = 0;
    }

    /**
     * Конструктор с параметрами.
     * @param engineType Тип двигателя
     * @param engineTorque Крутящий момент двигателя
     * @param engineName Название двигателя
     * @param enginePower Мощность двигателя
     */
    public InternalCombustionEngine(String engineType, int engineTorque,
                                    String engineName,
                                    double enginePower) {
        super(engineName, enginePower);

        this.engineType = engineType;
        this.engineTorque = engineTorque;
    }

    /**
     * Утсанавливает тип двигателя
     * @param engineType Тип двигателя
     */
    public void setEngineType(String engineType) {
        if (!engineType.isEmpty()) {
            this.engineType = engineType;
        }
    }

    /**
     * Устанавливает крутящий момент двигателя
     * @param engineTorque Крутящий момент двигателя
     */
    public void setEngineTorque(int engineTorque) {
        if (engineTorque != 0) {
            this.engineTorque = engineTorque;
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
        InternalCombustionEngine internalCombustionEngine =
                (InternalCombustionEngine) o;
        return engineTorque == internalCombustionEngine.engineTorque &&
                Objects.equals(engineType, internalCombustionEngine.engineType);
    }

    /**
     * Возвращает числовое значение фиксированной длины для любого объекта.
     * @return Числовое значение фиксированной длины для любого объекта.
     */
    @Override
    public int hashCode() {
        return Objects.hash(engineType, engineTorque);
    }

    /**
     * Вывод информации об объекте.
     * @return Информация объекта.
     */
    @Override
    public String toString() {
        return "┃ Двигатель внутреннего сгорания" + "\n" +
                "┃ Название двигателя: " + getEngineName() + "\n" +
                "┃ Мощность двигателя: " + getEnginePower() + "\n" +
                "┃ Тип ДВС: " + engineType + "\n" +
                "┃ Крутящий момент ДВС: " + engineTorque + "\n";
    }
}
