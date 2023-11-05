package org.example.lab_7_restclient;

import org.example.lab_7_restclient.exceptions.BikeException;
import org.example.lab_7_restclient.exceptions.BikeUpdateException;
import org.example.lab_7_restclient.models.Bike;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;

/**
 * Класс App демонстрирует работу с REST-сервисом, который управляет велосипедами.
 * Класс использует класс RestTemplate для отправки HTTP-запросов к сервису и класс
 * Bike для представления велосипедов.
 */
public class App {

    /**
     * Главный метод класса App. Выполняет различные операции с велосипедами, используя REST-сервис.
     *
     * @param args аргументы командной строки. Не используются в этом методе.
     * @throws BikeException если произошла ошибка при обновлении велосипеда.
     */
    public static void main(String[] args) throws BikeException {
        if (isServerRunning("http://localhost:8080")) {
            System.out.println("=========REST-клиент========");
            System.out.println("Добавим новый велосипед с помощью метода POST:");
            Bike bike = new Bike(null, 13000.14, 5, "Новый велосипед", "БМХ", "Алюминий");
            System.out.println(postBikeForObject(bike).toString());
            System.out.println("Обновим информацию об этом велосипеде с помощью метода UPDATE:");
            bike = new Bike(1, 280000.10, 3, "Другой велосипед", "Горный", "Карбон");
            updateBike(bike);
            System.out.println("Выведем информацию об обновленном велосипеде c id = 1 с помощью метода GET:");
            System.out.println(retrieveBike(1));
            System.out.println("Добавим еще один велосипед с помощью метода POST:");
            bike = new Bike(null, 54143.0, 8, "Старый велосипед", "Гоночный", "Сплав металлов");
            System.out.println(postBikeForObject(bike).toString());
            System.out.println("Выведем все велосипеды с помощью методы GET:");
            System.out.println(Arrays.toString(retrieveBicycles()));

            System.out.println("Удалим первый велосипед с помощью метода DELETE:");
            deleteBike(1);
            System.out.println("Ещё раз выведем все велосипеды с помощью метода GET:");
            System.out.println(retrieveBicyclesInString());

        } else {
            System.out.println("Сервер недоступен.");
        }
    }

    /**
     * Метод, который проверяет, запущен ли сервер по указанному URL.
     *
     * @param url URL сервера, который нужно проверить.
     * @return true, если сервер доступен, false, если нет.
     */
    public static boolean isServerRunning(String url) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getForObject(url, String.class);
            return true;
        } catch (RestClientException ex) {
            return false;
        }
    }

    /**
     * Метод, который возвращает информацию о велосипеде по его идентификатору.
     *
     * @param id идентификатор велосипеда, который нужно получить.
     * @return строковое представление велосипеда в формате JSON.
     */
    public static String retrieveBike(int id) {
        return new RestTemplate().getForObject(
                "http://localhost:8080/lab_7-1.0/bicycles/{id}",
                String.class, id
        );
    }

    /**
     * Метод, который возвращает массив всех велосипедов, хранящихся на сервере.
     *
     * @return массив объектов Bike, представляющих велосипеды.
     */
    public static Bike[] retrieveBicycles() {
        return new RestTemplate().getForObject(
                "http://localhost:8080/lab_7-1.0/bicycles",
                Bike[].class
        );
    }

    /**
     * Метод, который возвращает строковое представление всех велосипедов, хранящихся на сервере.
     *
     * @return строка в формате JSON, содержащая массив велосипедов.
     */
    public static String retrieveBicyclesInString() {
        return new RestTemplate().getForObject(
                "http://localhost:8080/lab_7-1.0/bicycles",
                String.class
        );
    }

    /**
     * Метод, который добавляет новый велосипед на сервер с помощью метода POST.
     *
     * @param bike объект Bike, представляющий велосипед, который нужно добавить.
     * @return объект Bike, представляющий добавленный велосипед с присвоенным идентификатором.
     */
    public static Bike postBikeForObject(Bike bike) {
        RestTemplate rest = new RestTemplate();
        return rest.postForObject(
                "http://localhost:8080/lab_7-1.0/bicycles",
                bike, Bike.class
        );
    }

    /**
     * Метод, который удаляет велосипед с сервера по его идентификатору с помощью метода DELETE.
     *
     * @param id идентификатор велосипеда, который нужно удалить.
     */
    public static void deleteBike(int id) {
        RestTemplate restTemplate = new RestTemplate();
        try {
            restTemplate.delete(
                    new
                            URI("http://localhost:8080/lab_7-1.0/bicycles/" + id));
        } catch (URISyntaxException wontHappen) {
        }
    }

    /**
     * Метод, который обновляет информацию о велосипеде на сервере с помощью метода PUT.
     * @param bike объект Bike, представляющий велосипед, который нужно обновить.
     * @throws BikeException если произошла ошибка при обновлении велосипеда.
     */
    public static void updateBike(Bike bike) throws
            BikeException {
        try {
            String url = "http://localhost:8080/lab_7-1.0/bicycles/" +
                    bike.getId();
            new RestTemplate().put(new URI(url), bike);
        } catch (URISyntaxException e) {
            throw new
                    BikeUpdateException("Невозможно обновить ВЕЛОСИПЕД!", e);
        }
    }
}
