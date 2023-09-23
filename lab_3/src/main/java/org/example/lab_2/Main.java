package org.example.lab_2;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Основной класс программы.
 */
public class Main {

    /**
     * Точка входа
     * @param args Массив аргументов
     */
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new
                AnnotationConfigApplicationContext(SpringConfig.class);

        RaceWeekend f1Weekend = context.getBean("f1WeekendBean",
                                                RaceWeekend.class);
        //RaceWeekend rdrcWeekend = context.getBean("rdrcWeekendBean",
        //                                          RaceWeekend.class);
        //RaceWeekend rdsWeekend = context.getBean("rdsWeekendBean",
        //                                         RaceWeekend.class);
        //RaceWeekend wrcWeekend = context.getBean("wrcWeekendBean",
        //                                         RaceWeekend.class);

        f1Weekend.startWeekend();
        //rdrcWeekend.startWeekend();
        //rdsWeekend.startWeekend();
        //wrcWeekend.startWeekend();
    }
}
