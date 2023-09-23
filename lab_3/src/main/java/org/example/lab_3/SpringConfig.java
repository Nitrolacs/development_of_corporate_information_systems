package org.example.lab_3;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Конфигурационный класс.
 */
@Configuration
@PropertySource("classpath:raceWeekend.properties")
public class SpringConfig {

    /**
     * Бин дрэг гонки
     * @param maxPower Максимальная мощность
     * @return Возвращает экземпляр DragRace
     */
    @Bean
    public DragRace dragRaceBean(@Value("${dragRace.maxPower}") int maxPower) {
        return DragRace.createDragRace(maxPower);
    }

    /**
     * Бин дрифт гонки
     * @param numberOfRacers Количество участников
     * @return Возвращает экземпляр DriftRace
     */
    @Bean
    public DriftRace driftRaceBean(@Value("${driftRace.numberOfRacers}")
                                   int numberOfRacers) {
        return new DriftRace(numberOfRacers);
    }

    /**
     * Бин кольцевой гонки
     * @param numberOfLaps Количество кругов
     * @return Возвращает экземпляр CircuitRace
     */
    @Bean
    public CircuitRace circuitRaceBean(@Value("${circuitRace.numberOfLaps}")
                                       int numberOfLaps) {
        return new CircuitRace(numberOfLaps);
    }

    /**
     * Бин раллийной гонки
     * @param trackName Название гоночной трассы
     * @return Возвращает экземпляр RallyRace
     */
    @Bean
    public RallyRace rallyRaceBean(@Value("${rallyRace.trackName}")
                                   String trackName) {
        return new RallyRace(trackName);
    }

    /**
     * Бин гонки Формулы 1
     * @param circuitRaceBean бин кольцевой гонки
     * @param name название гонки
     * @param durationInHours длительность в часах
     * @return Экземпляр
     */
    @Bean
    public RaceWeekend f1WeekendBean(CircuitRace circuitRaceBean,
                                     @Value("${f1Weekend.name}") String name,
                                     @Value("${f1Weekend.durationInHours}")
                                         int durationInHours) {
        RaceWeekend raceWeekend = new RaceWeekend(circuitRaceBean, name);
        raceWeekend.setDurationInHours(durationInHours);
        return raceWeekend;
    }

    /**
     * Бин гонки RDRC
     * @param dragRaceBean бин дрэг гонки
     * @param name название гонки
     * @param durationInHours длительность в часах
     * @return Экземпляр
     */
    @Bean
    public RaceWeekend rdrcWeekendBean(DragRace dragRaceBean,
                                       @Value("${rdrcWeekend.name}") String name,
                                       @Value("${rdrcWeekend.durationInHours}")
                                           int durationInHours) {
        RaceWeekend raceWeekend = new RaceWeekend(dragRaceBean, name);
        raceWeekend.setDurationInHours(durationInHours);
        return raceWeekend;
    }

    /**
     * Бин гонки RDS
     * @param driftRaceBean бин дрифт гонки
     * @param name название гонки
     * @param durationInHours длительность в часах
     * @return Экземпляр
     */
    @Bean
    public RaceWeekend rdsWeekendBean(DriftRace driftRaceBean,
                                      @Value("${rdsWeekend.name}") String name,
                                      @Value("${rdsWeekend.durationInHours}")
                                          int durationInHours) {
        RaceWeekend raceWeekend = new RaceWeekend(driftRaceBean, name);
        raceWeekend.setDurationInHours(durationInHours);
        return raceWeekend;
    }

    /**
     * Бин гонки WRC
     * @param rallyRaceBean бин раллийной гонки
     * @param name название гонки
     * @param durationInHours длительность в часах
     * @return Экземпляр
     */
    @Bean
    public RaceWeekend wrcWeekendBean(RallyRace rallyRaceBean,
                                      @Value("${wrcWeekend.name}") String name,
                                      @Value("${wrcWeekend.durationInHours}")
                                          int durationInHours) {
        RaceWeekend raceWeekend = new RaceWeekend(rallyRaceBean, name);
        raceWeekend.setDurationInHours(durationInHours);
        return raceWeekend;
    }
}
