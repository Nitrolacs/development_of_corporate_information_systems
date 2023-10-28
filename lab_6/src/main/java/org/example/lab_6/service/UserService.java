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

import java.util.Collections;
import java.util.HashSet;
import java.util.List;

/**
 * Сервис для работы с пользователями.
 * Этот класс реализует интерфейс UserDetailsService для загрузки
 * данных пользователя по его имени.
 */
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

    /**
     * Загрузка пользователя по его имени.
     *
     * @param username имя пользователя
     * @return объект UserDetails, содержащий данные пользователя
     * @throws UsernameNotFoundException если пользователь не найден
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        // Загрузка ролей пользователя
        List<Role> roles = jdbcTemplate.query(
                "SELECT r.id, r.name FROM t_role r INNER JOIN user_role ur ON r.id = ur.role_id WHERE ur.user_id = ?",
                new Object[]{user.getId()},
                (rs, rowNum) -> {
                    Role role = new Role();
                    role.setId(rs.getInt("id"));
                    role.setName(rs.getString("name"));
                    return role;
                }
        );

        user.setRoles(new HashSet<>(roles));

        return user;
    }

    /**
     * Поиск пользователя по его имени.
     *
     * @param username имя пользователя
     * @return объект User, содержащий данные пользователя, или null, если пользователь не найден
     */
    public User findByUsername(String username) {
        return jdbcTemplate.query("SELECT * FROM t_user WHERE username=?",
                        new Object[]{username}, new UserMapper()).
                stream().findAny().orElse(null);
    }

    /**
     * Поиск пользователя по его ID.
     *
     * @param id ID пользователя
     * @return объект User, содержащий данные пользователя, или null, если пользователь не найден
     */
    public User findUserById(int id) {
        return jdbcTemplate.query("SELECT * FROM t_user WHERE id=?",
                        new Object[]{id}, new UserMapper()).
                stream().findAny().orElse(null);
    }

    /**
     * Получение списка всех пользователей.
     *
     * @return список всех пользователей
     */
    public List<User> allUsers() {
        List<User> users = jdbcTemplate.query("SELECT * FROM t_user",
                new UserMapper());

        return users;
    }

    /**
     * Сохранение пользователя.
     *
     * @param user объект User, содержащий данные пользователя
     * @return true, если пользователь был успешно сохранен, false - если пользователь с таким именем уже существует
     */
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

    /**
     * Удаление пользователя.
     *
     * @param id ID пользователя
     * @return true, если пользователь был успешно удален, false - в противном случае
     */
    public boolean deleteUser(int id) {
        // Удалить связи между пользователем и его ролями
        jdbcTemplate.update("DELETE FROM user_role WHERE user_id=?", id);

        // Удалить связи между пользователем и его ролями
        int result = jdbcTemplate.update("DELETE FROM t_user WHERE id=?", id);
        return result == 1;
    }

    /**
     * Получение списка пользователей с ID больше указанного.
     *
     * @param idMin минимальное значение ID
     * @return список пользователей с ID больше указанного значения
     */
    public List<User> usergtList(int idMin) {
        return jdbcTemplate.query("SELECT * FROM t_user WHERE id > ?",
                new UserMapper(), idMin);
    }
}
