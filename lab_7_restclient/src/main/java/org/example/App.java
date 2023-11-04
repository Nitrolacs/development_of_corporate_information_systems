package org.example;

import org.springframework.web.client.RestTemplate;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        String ret = retrieveBike(1);
        System.out.println(ret.toString());
    }

    public static String retrieveBike(int id) {
        return new RestTemplate().getForObject(
                "http://localhost:8080/bicycles/{id}",
                String.class, id
        );
    }
}
