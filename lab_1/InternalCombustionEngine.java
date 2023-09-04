import java.util.Objects;

public class InternalCombustionEngine extends Engine {

    private String type;

    private int torque;

    public InternalCombustionEngine() {
        type = "default";
        torque = 0;
    }

    public InternalCombustionEngine(String type, int torque, String name,
                                    double power) {
        super(name, power);

        this.type = type;
        this.torque = torque;
    }

    public void setType(String type) {
        if (!type.isEmpty()) {
            this.type = type;
        }
    }

    public void setTorque(int torque) {
        if (torque != 0) {
            this.torque = torque;
        }
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InternalCombustionEngine internalCombustionEngine =
                (InternalCombustionEngine) o;
        return torque == internalCombustionEngine.torque &&
                Objects.equals(type, internalCombustionEngine.type);
    }

    public int hashCode() {
        return Objects.hash(type, torque);
    }

    public String toString() {
        return "┃ Объект: двигатель внутреннего сгорания" + "\n" +
               "┃ Название двигателя: " + getName() + "\n" +
               "┃ Мощность двигателя: " + getPower() + "\n" +
               "┃ Тип ДВС: " + type + "\n" +
               "┃ Крутящий момент ДВС: " + torque + "\n";
    }
}
