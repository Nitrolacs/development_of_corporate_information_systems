package src.main.java.engines;

import java.util.Objects;

public class JetEngine extends Engine {

    private String engineFuel;

    private double engineSpecificImpulse;  // удельный импульс

    public JetEngine() {
        engineFuel = "default";
        engineSpecificImpulse = 0.0;
    }

    public JetEngine(String engineFuel, double engineSpecificImpulse, String engineName,
                     double enginePower) {
        super(engineName, enginePower);

        this.engineFuel = engineFuel;
        this.engineSpecificImpulse = engineSpecificImpulse;
    }

    public void setEngineFuel(String engineFuel) {
        if (!engineFuel.isEmpty()) {
            this.engineFuel = engineFuel;
        }
    }

    public void setEngineSpecificImpulse(double engineSpecificImpulse) {
        if (engineSpecificImpulse != 0.0) {
            this.engineSpecificImpulse = engineSpecificImpulse;
        }
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JetEngine jetEngine = (JetEngine) o;
        return Double.compare(jetEngine.engineSpecificImpulse, engineSpecificImpulse) == 0 &&
                Objects.equals(engineFuel, jetEngine.engineFuel);
    }

    public int hashCode() {
        return Objects.hash(engineFuel, engineSpecificImpulse);
    }

    public String toString() {
        return "┃ Реактивный двигатель" + "\n" +
                "┃ Название двигателя: " + getEngineName() + "\n" +
                "┃ Мощность двигателя: " + getEnginePower() + "\n" +
                "┃ Топливо: " + engineFuel + "\n" +
                "┃ Удельный импульс: " + engineSpecificImpulse + "\n";
    }
}
