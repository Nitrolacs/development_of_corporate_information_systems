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

        Bike bike = new Bike(0, 0.0, 23, "Новый", "БМХ", "Железо");


        System.out.println(Arrays.toString(retrieveBicycles()));
        System.out.println(postBikeForObject(bike).toString());
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
        return rest.postForObject(
                "http://localhost:8080/bicycles",
                bike, Bike.class
        );
    }
}
