package org.example.lab_4;

public class Bike {
    private Integer id;

    private Double price;
    private Integer numberOfSpeeds;

    private String name;
    private String type;
    private String frameMaterial;

    public Bike() {
        this.id = 1;
        this.price = 1.0;
        this.numberOfSpeeds = 1;
        this.name = "default";
        this.type = "default";
        this.frameMaterial = "default";
    }

    public Bike(Integer id, Double price, Integer numberOfSpeeds, String name,
                String type, String frameMaterial) {
        this.id = id;
        this.price = price;
        this.numberOfSpeeds = numberOfSpeeds;
        this.name = name;
        this.type = type;
        this.frameMaterial = frameMaterial;
    }

    public Integer getId() {
        return id;
    }

    public Double getPrice() {
        return price;
    }

    public Integer getNumberOfSpeeds() {
        return numberOfSpeeds;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getFrameMaterial() {
        return frameMaterial;
    }

    public void setId(Integer id) {
        if (id != 0) {
            this.id = id;
        }
    }

    public void setPrice(Double price) {
        if (price != 0) {
            this.price = price;
        }
    }

    public void setNumberOfSpeeds(Integer numberOfSpeeds) {
        if (numberOfSpeeds != 0) {
            this.numberOfSpeeds = numberOfSpeeds;
        }
    }

    public void setName(String name) {
        if (!name.isEmpty()) {
            this.name = name;
        }
    }

    public void setType(String type) {
        if (!type.isEmpty()) {
            this.type = type;
        }
    }

    public void setFrameMaterial(String frameMaterial) {
        if (!frameMaterial.isEmpty()) {
            this.frameMaterial = frameMaterial;
        }
    }

    /**
     * Вывод информации об объекте.
     * @return Информация объекта.
     */
    @Override
    public String toString() {
        return "┃ Велосипед" + "\n" +
                "┃ Название велосипеда: " + getName() + "\n" +
                "┃ Id велосипеда: " + getId() + "\n" +
                "┃ Цена велосипеда: " + getPrice() + "\n" +
                "┃ Количество скоростей: " + getNumberOfSpeeds() + "\n" +
                "┃ Тип велосипеда: " + getType() + "\n" +
                "┃ Материал рамы: " + getFrameMaterial() + "\n";
    }
}
