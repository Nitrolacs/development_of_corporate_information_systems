package src.main.java.engines;

import java.util.Objects;

public class InternalCombustionEngine extends Engine {

    private String engineType;

    private int engineTorque;

    public InternalCombustionEngine() {
        engineType = "default";
        engineTorque = 0;
    }

    public InternalCombustionEngine(String engineType, int engineTorque,
                                    String engineName,
                                    double enginePower) {
        super(engineName, enginePower);

        this.engineType = engineType;
        this.engineTorque = engineTorque;
    }

    public void setEngineType(String engineType) {
        if (!engineType.isEmpty()) {
            this.engineType = engineType;
        }
    }

    public void setEngineTorque(int engineTorque) {
        if (engineTorque != 0) {
            this.engineTorque = engineTorque;
        }
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InternalCombustionEngine internalCombustionEngine =
                (InternalCombustionEngine) o;
        return engineTorque == internalCombustionEngine.engineTorque &&
                Objects.equals(engineType, internalCombustionEngine.engineType);
    }

    public int hashCode() {
        return Objects.hash(engineType, engineTorque);
    }

    public String toString() {
        return "┃ Двигатель внутреннего сгорания" + "\n" +
                "┃ Название двигателя: " + getEngineName() + "\n" +
                "┃ Мощность двигателя: " + getEnginePower() + "\n" +
                "┃ Тип ДВС: " + engineType + "\n" +
                "┃ Крутящий момент ДВС: " + engineTorque + "\n";
    }
}
