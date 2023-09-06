package src.main.java.engines;

import java.util.Objects;

public class Engine {

    private String engineName;

    private double enginePower;

    public Engine() {
        engineName = "default";
        enginePower = 0.0;
    }

    public Engine(String engineName, double enginePower) {
        this.engineName = engineName;
        this.enginePower = enginePower;
    }

    public String getEngineName() {
        return engineName;
    }

    public double getEnginePower() {
        return enginePower;
    }

    public void setEngineName(String engineName) {
        if (!engineName.isEmpty()) {
            this.engineName = engineName;
        }
    }

    public void setEnginePower(double enginePower) {
        if (enginePower != 0.0) {
            this.enginePower = enginePower;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Engine engine = (Engine) o;
        return Double.compare(engine.enginePower, enginePower) == 0 &&
                Objects.equals(engineName, engine.engineName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(engineName, enginePower);
    }

    @Override
    public String toString() {
        return "┃ Двигатель" + "\n" +
               "┃ Название двигателя: " + engineName + "\n" +
               "┃ Мощность двигателя: " + enginePower + "\n";
    }

}
