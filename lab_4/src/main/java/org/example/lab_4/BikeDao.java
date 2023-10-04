package org.example.lab_4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Класс доступа к данным
 */
@Component
public class BikeDao {

    /**
     * Поле jdbcTemplate
     */
    private final JdbcTemplate jdbcTemplate;

    /**
     * Внедрение через конструктор
     * @param jdbcTemplate jdbcTemplate
     */
    @Autowired
    public BikeDao(JdbcTemplate jdbcTemplate) {
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
     * Проверяет, есть ли в таблице запись с таким id
     * @param id id
     * @return Результат поиска записи
     */
    public boolean isBikeExists(int id) {
        int result = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM bike where id=?", new Object[] { id }, Integer.class);
        return result > 0;
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
}
