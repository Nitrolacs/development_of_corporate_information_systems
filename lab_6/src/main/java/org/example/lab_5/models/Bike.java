package org.example.lab_5.models;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Класс велосипеда
 */
public class Bike {

    /**
     * Id велосипеда
     */
    private Integer id;

    /**
     * Цена велосипеда
     */
    @Min(value = 0, message = "Цена велосипеда не может быть меньше 0!")
    private double price;

    /**
     * Количество скоростей у велосипеда
     */
    @Min(value = 1, message = "Количество скоростей не может быть меньше 1!")
    private int numberOfSpeeds;

    /**
     * Название велосипеда
     */
    @NotEmpty(message = "Название не может быть пустым!")
    @Size(min = 2, max = 150, message = "Название должно быть длиной от " +
            "2 до 150 символов!")
    private String name;

    /**
     * Тип велосипеда
     */
    @NotEmpty(message = "Тип велосипеда не может быть пустым!")
    @Size(min = 2, max = 100, message = "Тип велосипеда должен быть длиной от" +
            "2 до 100 символов")
    private String type;

    /**
     * Материал рамы велосипеда
     */
    @NotEmpty(message = "Материал рамы велосипеда не может быть пустым!")
    @Size(min = 2, max = 100, message = "Материал рамы должен быть длиной от" +
            "2 до 100 символов")
    private String frameMaterial;

    /**
     * Конструктор без параметров
     */
    public Bike() {
    }

    /**
     * Конструктор с параметрами
     * @param id Id велосипеда (генерируется автоматически в базе данных)
     * @param price Цена велосипеда
     * @param numberOfSpeeds Количество скоростей у велосипеда
     * @param name Название велосипеда
     * @param type Тип велосипеда
     * @param frameMaterial Материал рамы велосипеда
     */
    public Bike(Integer id, Double price, Integer numberOfSpeeds, String name,
                String type, String frameMaterial) {
        this.id = id;
        this.price = price;
        this.numberOfSpeeds = numberOfSpeeds;
        this.name = name;
        this.type = type;
        this.frameMaterial = frameMaterial;
    }

    /**
     * Возвращает id
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * Возвращает цену
     * @return цена
     */
    public Double getPrice() {
        return price;
    }

    /**
     * Возвращает количество скоростей у велосипеда
     * @return количество скоростей
     */
    public Integer getNumberOfSpeeds() {
        return numberOfSpeeds;
    }

    /**
     * Возвращает название велосипеда
     * @return название велосипеда
     */
    public String getName() {
        return name;
    }

    /**
     * Возвращает тип велосипеда
     * @return тип велосипеда
     */
    public String getType() {
        return type;
    }

    /**
     * Возвращает материал рамы у велосипеда
     * @return материал рамы у велосипеда
     */
    public String getFrameMaterial() {
        return frameMaterial;
    }

    /**
     * Устанавливает id
     * @param id id
     */
    public void setId(Integer id) {
        if (id != 0) {
            this.id = id;
        }
    }

    /**
     * Устанавливает цену
     * @param price цена
     */
    public void setPrice(Double price) {
        if (price != 0) {
            this.price = price;
        }
    }

    /**
     * Устанавливает количество скоростей
     * @param numberOfSpeeds количество скоростей у велосипеда
     */
    public void setNumberOfSpeeds(Integer numberOfSpeeds) {
        if (numberOfSpeeds != 0) {
            this.numberOfSpeeds = numberOfSpeeds;
        }
    }

    /**
     * Устанавливает название велосипеда
     * @param name название велосипеда
     */
    public void setName(String name) {
        if (!name.isEmpty()) {
            this.name = name;
        }
    }

    /**
     * Устанавливает тип велосипеда
     * @param type тип велосипеда
     */
    public void setType(String type) {
        if (!type.isEmpty()) {
            this.type = type;
        }
    }

    /**
     * Устанавливает материал рамы велосипеда
     * @param frameMaterial материал рамы
     */
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
        return  "┃ Название велосипеда: " + getName() + "\n" +
                "┃ Id велосипеда: " + getId() + "\n" +
                "┃ Цена велосипеда: " + getPrice() + "\n" +
                "┃ Количество скоростей: " + getNumberOfSpeeds() + "\n" +
                "┃ Тип велосипеда: " + getType() + "\n" +
                "┃ Материал рамы: " + getFrameMaterial();
    }
}
