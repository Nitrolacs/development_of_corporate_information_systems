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

    public int delete(int id) {
        int result = jdbcTemplate.update("DELETE FROM bike WHERE id=?", id);
        return result;
    }

    public void update(int id, Bike newBike) {
        jdbcTemplate.update("UPDATE bike SET price=?, numberOfSpeeds=?, " +
                "name=?, type=?, frameMaterial=? WHERE id=?", newBike.getPrice(),
                newBike.getNumberOfSpeeds(), newBike.getName(), newBike.getType(),
                newBike.getFrameMaterial(), id);
    }

    public boolean isBikeExists(int id) {
        int result = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM bike where id=?", new Object[] { id }, Integer.class);
        return result > 0;
    }

    public List<Bike> findAllBicyclesWherePriceIsLower(Double price) {
        List<Bike> bicycles = jdbcTemplate.query("SELECT * FROM bike WHERE price < ?",
                new BeanPropertyRowMapper<>(Bike.class), price);

        return bicycles;
    }
}
