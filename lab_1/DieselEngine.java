import java.util.Objects;

public class DieselEngine extends Engine {

    private String engineManufacturer;

    private double engineDisplacement; // рабочий объём

    public DieselEngine() {
        engineManufacturer = "default";
        engineDisplacement = 0.0;
    }

    public DieselEngine(String engineManufacturer, double engineDisplacement,
                        String engineName, double enginePower) {
        super(engineName, enginePower);

        this.engineManufacturer = engineManufacturer;
        this.engineDisplacement = engineDisplacement;
    }

    public void setEngineManufacturer(String engineManufacturer) {
        if (!engineManufacturer.isEmpty()) {
            this.engineManufacturer = engineManufacturer;
        }
    }

    public void setEngineDisplacement(double engineDisplacement) {
        if (engineDisplacement != 0) {
            this.engineDisplacement = engineDisplacement;
        }
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DieselEngine dieselEngine = (DieselEngine) o;
        return Double.compare(dieselEngine.engineDisplacement, engineDisplacement) == 0 &&
                Objects.equals(engineManufacturer, dieselEngine.engineManufacturer);
    }

    public int hashCode() {
        return Objects.hash(engineManufacturer, engineDisplacement);
    }


    public String toString() {
        return "┃ Объект: реактивный двигатель" + "\n" +
                "┃ Название двигателя: " + getEngineName() + "\n" +
                "┃ Мощность двигателя: " + getEnginePower() + "\n" +
                "┃ Производитель двигателя: " + engineManufacturer + "\n" +
                "┃ Рабочий объём двигателя: " + engineDisplacement + "\n";
    }
}
