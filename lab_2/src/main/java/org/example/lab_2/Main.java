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

        RaceWeekend F1Weekend = context.getBean("F1WeekendBean",
                                                RaceWeekend.class);
        RaceWeekend RDRCWeekend = context.getBean("RDRCWeekendBean",
                                                  RaceWeekend.class);
        RaceWeekend RDSWeekend = context.getBean("RDSWeekend",
                                                 RaceWeekend.class);
        RaceWeekend WRCWeekend = context.getBean("WRCWeekend",
                                                 RaceWeekend.class);

        F1Weekend.startWeekend();
        RDRCWeekend.startWeekend();
        RDSWeekend.startWeekend();
        WRCWeekend.startWeekend();
    }
}
