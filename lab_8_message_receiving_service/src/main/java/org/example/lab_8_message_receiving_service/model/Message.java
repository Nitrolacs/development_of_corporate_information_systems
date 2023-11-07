package org.example.lab_8_message_receiving_service.model;

import java.io.Serializable;

public class Message implements Serializable {
    private String message;
    private Bike bike;

    public Message() {
        message = "";
        bike = new Bike();
    }

    public Message(String message, Bike bike) {
        this.message = message;
        this.bike = bike;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Bike getBike() {
        return bike;
    }

    public void setBike(Bike bike) {
        this.bike = bike;
    }
}
