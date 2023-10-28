package org.example.lab_6.dao;

import org.example.lab_6.models.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Реализация интерфейса RowMapper для сущности User.
 * Этот класс предназначен для отображения строки ResultSet на объект User.
 */
public class UserMapper implements RowMapper<User> {

    /**
     * Метод для отображения строки ResultSet на объект User.
     * @param rs     объект ResultSet, содержащий данные из базы данных
     * @param rowNum номер строки в ResultSet
     * @return объект User, заполненный данными из строки ResultSet
     * @throws SQLException если произошла ошибка при чтении данных из ResultSet
     */
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        return user;
    }
}
