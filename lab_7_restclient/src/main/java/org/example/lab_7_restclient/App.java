package org.example.lab_7_restclient;

import org.example.lab_7_restclient.exceptions.BikeException;
import org.example.lab_7_restclient.exceptions.BikeUpdateException;
import org.example.lab_7_restclient.models.Bike;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;

public class App {
    public static void main(String[] args) throws BikeException {
        if (isServerRunning("http://localhost:8080")) {
            Bike bike = new Bike(4, 0.0, 10, "УРАААА", "БМХ", "Железо");
            updateBike(bike);
            String ret = retrieveBike(1);
            System.out.println(ret);
            System.out.println(Arrays.toString(retrieveBicycles()));
            System.out.println(retrieveBicyclesInString());

            bike = new Bike(null, 0.0, 10, "Новый", "БМХ", "Железо");
            System.out.println(postBikeForObject(bike).toString());
            deleteBike(6);
            System.out.println(retrieveBike(6));

        } else {
            System.out.println("Сервер недоступен.");
        }
    }

    public static boolean isServerRunning(String url) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getForObject(url, String.class);
            return true;
        } catch (RestClientException ex) {
            return false;
        }
    }

    public static String retrieveBike(int id) {
        return new RestTemplate().getForObject(
                "http://localhost:8080/bicycles/{id}",
                String.class, id
        );
    }

    public static Bike[] retrieveBicycles() {
        return new RestTemplate().getForObject(
                "http://localhost:8080/bicycles",
                Bike[].class
        );
    }

    public static String retrieveBicyclesInString() {
        return new RestTemplate().getForObject(
                "http://localhost:8080/bicycles",
                String.class
        );
    }


    public static Bike postBikeForObject(Bike bike) {
        RestTemplate rest = new RestTemplate();
        return rest.postForObject(
                "http://localhost:8080/bicycles",
                bike, Bike.class
        );
    }

    public static void deleteBike(int id) {
        RestTemplate restTemplate = new RestTemplate();
        try {
            restTemplate.delete(
                    new
                            URI("http://localhost:8080/bicycles/" + id));
        } catch (URISyntaxException wontHappen) {
        }
    }

    public static void updateBike(Bike bike) throws
            BikeException {
        try {
            String url = "http://localhost:8080/bicycles/" +
                    bike.getId();
            new RestTemplate().put(new URI(url), bike);
        } catch (URISyntaxException e) {
            throw new
                    BikeUpdateException("Невозможно обновить велосипед!", e);
        }
    }
}
