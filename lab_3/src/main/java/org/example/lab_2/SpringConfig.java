package org.example.lab_2;

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
     * @return возвращает экземпляр DragRace
     */
    @Bean
    public DragRace dragRaceBean(@Value("${dragRace.maxPower}") int maxPower) {
        return DragRace.createDragRace(maxPower);
    }

    @Bean
    public DriftRace driftRaceBean(@Value("${driftRace.numberOfRacers}")
                                   int numberOfRacers) {
        return new DriftRace(numberOfRacers);
    }

    @Bean
    public CircuitRace circuitRaceBean(@Value("${circuitRace.numberOfLaps}")
                                       int numberOfLaps) {
        return new CircuitRace(numberOfLaps);
    }

    @Bean
    public RallyRace rallyRaceBean(@Value("${rallyRace.trackName}")
                                   String trackName) {
        return new RallyRace(trackName);
    }

    @Bean
    public RaceWeekend f1WeekendBean(CircuitRace circuitRaceBean,
                                     @Value("${f1Weekend.name}") String name) {
        return new RaceWeekend(circuitRaceBean, name);
}
}
