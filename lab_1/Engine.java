import java.util.Objects;

public class Engine {

    private String name;

    private double power;

    public Engine() {
        name = "default";
        power = 0.0;
    }

    public Engine(String name, double power) {
        this.name = name;
        this.power = power;
    }

    public String getName() {
        return name;
    }

    public double getPower() {
        return power;
    }

    public void setName(String name) {
        if (!name.isEmpty()) {
            this.name = name;
        }
    }

    public void setPower(double power) {
        if (power != 0.0) {
            this.power = power;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Engine engine = (Engine) o;
        return Double.compare(engine.power, power) == 0 &&
                Objects.equals(name, engine.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, power);
    }

    @Override
    public String toString() {
        return "┃ Объект: двигатель" + "\n" +
               "┃ Название двигателя: " + name + "\n" +
               "┃ Мощность двигателя: " + power + "\n";
    }

}
