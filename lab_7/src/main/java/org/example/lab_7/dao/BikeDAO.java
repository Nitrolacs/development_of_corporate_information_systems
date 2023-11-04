package org.example.lab_7.dao;

import org.example.lab_7.models.Bike;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

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
     * @param jdbcTemplate jdbcTemplate
     */
    @Autowired
    public BikeDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Выполняет запрос на получение всех записей из таблицы
     * @return Список записей
     */
    public List<Bike> findAll() {
        List<Bike> bicycles = jdbcTemplate.query("SELECT * FROM bike",
                new BeanPropertyRowMapper<>(Bike.class));

        return bicycles;
    }

    /**
     * Выполняет вставку новой записи в таблицу базы данных
     * @param bike Объект с данными для вставки
     */
    public void insert(Bike bike) {
        jdbcTemplate.update("INSERT INTO bike " +
                        "(price, numberOfSpeeds, name, type, frameMaterial)" +
                        " VALUES(?, ?, ?, ?, ?)",
                bike.getPrice(), bike.getNumberOfSpeeds(), bike.getName(),
                bike.getType(), bike.getFrameMaterial());
    }

    /**
     * Выполняет удаление записи по её id
     * @param id id
     * @return Результат удаления
     */
    public int delete(int id) {
        int result = jdbcTemplate.update("DELETE FROM bike WHERE id=?", id);
        return result;
    }

    /**
     * Выполняет обновление записи в таблицы
     * @param id id
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
     * @param id идентификатор записи
     * @return объект найденной записи.
     */
    public Bike getBikeById(int id) {
        return jdbcTemplate.query("SELECT * FROM Bike WHERE id=?",
                new Object[]{id}, new BeanPropertyRowMapper<>(Bike.class)).
                stream().findAny().orElse(null);
    }
}
