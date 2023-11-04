package org.example.lab_7_restclient;

import org.example.lab_7_restclient.models.Bike;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class App {
    public static void main(String[] args) {
        String ret = retrieveBike(1);
        System.out.println(ret);

        Bike bike = new Bike();
        bike.setName("Тестовый");
        bike.setFrameMaterial("ЖЕЛЕЗООО");
        bike.setType("ГОНОЧНЫЫЫЙ");
        bike.setPrice(10000.10);
        bike.setNumberOfSpeeds(10);
        //System.out.println(postBikeForObject(bike).toString());

        System.out.println(Arrays.toString(retrieveBicycles()));
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


    public static Bike postBikeForObject(Bike bike) {
        RestTemplate rest = new RestTemplate();
        return new RestTemplate().postForObject(
                "http://localhost:8080/bicycles",
                bike, Bike.class
        );
    }

    public void updateBike(Bike bike) {
        try {
            String url = "http://localhost:8080/bicycles" + bike.getId();
            new RestTemplate().put(new URI(url), bike);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
