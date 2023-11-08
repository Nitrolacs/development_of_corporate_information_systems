package org.example.lab_8.models;

import java.io.Serializable;

/**
 * Класс Message представляет собой сериализуемое сообщение, содержащее строку и объект Bike.
 */
public class Message implements Serializable {

    /**
     * Текстовое сообщение
     */
    private String message;

    /**
     * Запись из бд
     */
    private Bike bike;

    /**
     * Конструктор по умолчанию, который инициализирует сообщение пустой строкой и новым объектом Bike.
     */
    public Message() {
        message = "";
        bike = new Bike();
    }

    /**
     * Конструктор, который инициализирует сообщение и объект Bike переданными значениями.
     * @param message Строка сообщения.
     * @param bike Объект Bike.
     */
    public Message(String message, Bike bike) {
        this.message = message;
        this.bike = bike;
    }

    /**
     * Этот метод возвращает сообщение.
     * @return Возвращает строку сообщения.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Этот метод устанавливает сообщение.
     * @param message Строка сообщения.
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Этот метод возвращает объект Bike.
     * @return Возвращает объект Bike.
     */
    public Bike getBike() {
        return bike;
    }

    /**
     * Этот метод устанавливает объект Bike.
     * @param bike Объект Bike.
     */
    public void setBike(Bike bike) {
        this.bike = bike;
    }
}
