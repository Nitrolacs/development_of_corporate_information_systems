package org.example.lab_2;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSpring {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new
        ClassPathXmlApplicationContext("applicationContext.xml");

        Race testRace = context.getBean("testRace", Race.class);

        System.out.println(testRace.getName());

        context.close();
    }
}
