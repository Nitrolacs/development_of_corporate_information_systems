package org.example.lab_7_restclient.exceptions;

public class BikeException extends Exception {
    public BikeException(String message) {
        super(message);
    }

    public BikeException(String message, Throwable cause) {
        super(message, cause);
    }
}
