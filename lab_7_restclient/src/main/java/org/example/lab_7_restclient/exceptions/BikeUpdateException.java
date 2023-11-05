package org.example.lab_7_restclient.exceptions;

/**
 * Класс BikeUpdateException представляет собой специфический класс исключений, связанных с обновлением велосипедов.
 * Класс наследуется от класса BikeException и может быть использован для обработки ошибок, возникающих при попытке обновить информацию о велосипеде на сервере.
 */
public class BikeUpdateException extends BikeException {

    /**
     * Конструктор класса BikeUpdateException, принимающий сообщение об ошибке и причину исключения.
     *
     * @param message сообщение об ошибке.
     * @param cause   причина исключения.
     */
    public BikeUpdateException(String message, Throwable cause) {
        super(message, cause);
    }
}