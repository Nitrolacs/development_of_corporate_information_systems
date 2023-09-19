package org.example.lab_2;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Основной класс программы.
 */
public class Main {

    /**
     * Точка входа
     * @param args Массив аргументов
     */
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new
                ClassPathXmlApplicationContext("applicationContext.xml");

        RaceWeekend f1Weekend = context.getBean("f1WeekendBean",
                                                RaceWeekend.class);
        RaceWeekend rdrcWeekend = context.getBean("rdrcWeekendBean",
                                                  RaceWeekend.class);
        RaceWeekend rdsWeekend = context.getBean("rdsWeekend",
                                                 RaceWeekend.class);
        RaceWeekend wrcWeekend = context.getBean("wrcWeekend",
                                                 RaceWeekend.class);

        f1Weekend.startWeekend();
        rdrcWeekend.startWeekend();
        rdsWeekend.startWeekend();
        wrcWeekend.startWeekend();
    }
}
