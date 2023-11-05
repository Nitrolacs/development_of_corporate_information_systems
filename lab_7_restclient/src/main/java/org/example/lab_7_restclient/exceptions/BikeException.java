package org.example.lab_7_restclient.exceptions;

/**
 * Класс BikeException представляет собой общий класс исключений, связанных с велосипедами.
 * Класс наследуется от класса Exception и может быть использован для обработки различных ошибок, возникающих при работе с велосипедами.
 */
public class BikeException extends Exception {

    /**
     * Конструктор класса BikeException, принимающий сообщение об ошибке.
     *
     * @param message сообщение об ошибке.
     */
    public BikeException(String message) {
        super(message);
    }

    /**
     * Конструктор класса BikeException, принимающий сообщение об ошибке и причину исключения.
     * @param message сообщение об ошибке.
     * @param cause причина исключения.
     */
    public BikeException(String message, Throwable cause) {
        super(message, cause);
    }
}
