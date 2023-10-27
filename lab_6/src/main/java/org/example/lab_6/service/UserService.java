package org.example.lab_6.service;

import org.example.lab_6.dao.RoleMapper;
import org.example.lab_6.dao.UserMapper;
import org.example.lab_6.models.Role;
import org.example.lab_6.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

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
    public UserService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return user;
    }

    public User findByUsername(String username) {
        return jdbcTemplate.query("SELECT * FROM t_user WHERE username=?",
                        new Object[]{username}, new UserMapper()).
                stream().findAny().orElse(null);
    }

    public User findUserById(int id) {
        return jdbcTemplate.query("SELECT * FROM t_user WHERE id=?",
                        new Object[]{id}, new UserMapper()).
                stream().findAny().orElse(null);
    }

    public List<User> allUsers() {
        List<User> users = jdbcTemplate.query("SELECT * FROM t_user",
                new UserMapper());

        return users;
    }

    public boolean saveUser(User user) {
        User userFromDB = findByUsername(user.getUsername());

        if (userFromDB != null) {
            return false;
        }

        // Получить роль из базы данных
        Role userRole = jdbcTemplate.query("SELECT * FROM t_role WHERE id=?",
                        new Object[]{1}, new RoleMapper()).
                stream().findAny().orElse(null);

        if (userRole != null) {
            user.setRoles(Collections.singleton(userRole));
        }

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        // Сохранить пользователя
        jdbcTemplate.update("INSERT INTO t_user " +
                        "(username, password)" +
                        " VALUES(?, ?)",
                user.getUsername(), user.getPassword());

        // Получить ID вновь созданного пользователя
        int userId = findByUsername(user.getUsername()).getId();

        // Сохранить связь между пользователем и его ролями
        for (Role role : user.getRoles()) {
            jdbcTemplate.update("INSERT INTO user_role " +
                                "(user_id, role_id)" +
                                " VALUES(?, ?)",
                    userId, role.getId());
        }

        return true;
    }


    public boolean deleteUser(int id) {
        // Удалить связи между пользователем и его ролями
        jdbcTemplate.update("DELETE FROM user_role WHERE user_id=?", id);

        // Удалить связи между пользователем и его ролями
        int result = jdbcTemplate.update("DELETE FROM t_user WHERE id=?", id);
        return result == 1;
    }

    public List<User> usergtList(int idMin) {
        return jdbcTemplate.query("SELECT * FROM t_user WHERE id > ?",
                new UserMapper(), idMin);
    }
}
