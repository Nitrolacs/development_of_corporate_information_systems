package org.example.lab_6.dao;

import org.example.lab_6.models.Role;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Реализация интерфейса RowMapper для сущности Role.
 * Этот класс предназначен для отображения строки ResultSet на объект Role.
 */
public class RoleMapper implements RowMapper<Role> {

    /**
     * Метод для отображения строки ResultSet на объект Role.
     * @param rs     объект ResultSet, содержащий данные из базы данных
     * @param rowNum номер строки в ResultSet
     * @return объект Role, заполненный данными из строки ResultSet
     * @throws SQLException если произошла ошибка при чтении данных из ResultSet
     */
    @Override
    public Role mapRow(ResultSet rs, int rowNum) throws SQLException {
        Role role = new Role();
        role.setId(rs.getInt("id"));
        role.setName(rs.getString("name"));
        return role;
    }
}
