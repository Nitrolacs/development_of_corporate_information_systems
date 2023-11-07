package org.example.lab_8.dao;

import org.example.lab_8.models.Bike;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

/**
 * Класс доступа к данным
 */
@Component
public class BikeDAO {

    /**
     * Поле jdbcTemplate
     */
    private final JdbcTemplate jdbcTemplate;

    /**
     * Внедрение через конструктор
     *
     * @param jdbcTemplate jdbcTemplate
     */
    @Autowired
    public BikeDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Выполняет запрос на получение всех записей из таблицы
     *
     * @return Список записей
     */
    public List<Bike> getBicycles() {
        List<Bike> bicycles = jdbcTemplate.query("SELECT * FROM bike",
                new BeanPropertyRowMapper<>(Bike.class));

        return bicycles;
    }

    /**
     * Выполняет запрос на вставку новой записи в таблицу
     *
     * @param bike объект, который нужно вставить в таблицу
     * @return возвращает вставленный объект
     */
    public Bike insert(Bike bike) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO bike " +
                    "(price, numberOfSpeeds, name, type, frameMaterial)" +
                    " VALUES(?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            ps.setDouble(1, bike.getPrice());
            ps.setInt(2, bike.getNumberOfSpeeds());
            ps.setString(3, bike.getName());
            ps.setString(4, bike.getType());
            ps.setString(5, bike.getFrameMaterial());
            return ps;
        }, keyHolder);

        Map<String, Object> keys = keyHolder.getKeys();
        if (keys != null) {
            bike.setId((Integer) keys.get("id"));
        }
        return bike;
    }

    /**
     * Выполняет удаление записи по её id
     *
     * @param id id
     * @return Результат удаления
     */
    public int delete(int id) {
        int result = jdbcTemplate.update("DELETE FROM bike WHERE id=?", id);
        return result;
    }

    /**
     * Выполняет обновление записи в таблицы
     *
     * @param id      id
     * @param newBike Новые данные
     */
    public void update(int id, Bike newBike) {
        jdbcTemplate.update("UPDATE bike SET price=?, numberOfSpeeds=?, " +
                        "name=?, type=?, frameMaterial=? WHERE id=?", newBike.getPrice(),
                newBike.getNumberOfSpeeds(), newBike.getName(), newBike.getType(),
                newBike.getFrameMaterial(), id);
    }

    /**
     * Находит все записи, цена которой ниже чем в аргументе
     *
     * @param price Цена
     * @return Список записей, удовлетворяющих условию
     */
    public List<Bike> findAllBicyclesWherePriceIsLower(Double price) {
        List<Bike> bicycles = jdbcTemplate.query("SELECT * FROM bike WHERE price < ?",
                new BeanPropertyRowMapper<>(Bike.class), price);

        return bicycles;
    }

    /**
     * Вывод информацию по записи с id.
     *
     * @param id идентификатор записи
     * @return объект найденной записи.
     */
    public Bike getBikeById(int id) {
        return jdbcTemplate.query("SELECT * FROM Bike WHERE id=?",
                        new Object[]{id}, new BeanPropertyRowMapper<>(Bike.class)).
                stream().findAny().orElse(null);
    }
}
