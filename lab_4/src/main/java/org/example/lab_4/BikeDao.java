package org.example.lab_4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BikeDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BikeDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Bike> findAll() {
        List<Bike> bicycles = jdbcTemplate.query("SELECT * FROM bike",
                new BeanPropertyRowMapper<>(Bike.class));

        return bicycles;
    }

    public void insert(Bike bike) {
        jdbcTemplate.update("INSERT INTO bike " +
                        "(price, numberOfSpeeds, name, type, frameMaterial)" +
                        " VALUES(?, ?, ?, ?, ?)",
                bike.getPrice(), bike.getNumberOfSpeeds(), bike.getName(),
                bike.getType(), bike.getFrameMaterial());
    }
}
