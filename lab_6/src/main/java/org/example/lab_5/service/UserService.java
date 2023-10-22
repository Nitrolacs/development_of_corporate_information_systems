package org.example.lab_5.service;

import org.example.lab_5.dao.UserMapper;
import org.example.lab_5.models.Role;
import org.example.lab_5.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

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
        User user = jdbcTemplate.query("SELECT * FROM t_user WHERE username=?",
                        new Object[]{username}, new UserMapper()).
                stream().findAny().orElse(null);

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

        user.setRoles(Collections.singleton(new Role(1, "ROLE_USER")));
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        jdbcTemplate.update("INSERT INTO t_user " +
                        "(username, password)" +
                        " VALUES(?, ?)",
                user.getUsername(), user.getPassword());
        return true;
    }

    public boolean deleteUser(int id) {
        int result = jdbcTemplate.update("DELETE FROM t_user WHERE id=?", id);
        return result == 1;
    }

    public List<User> usergtList(int idMin) {
        return jdbcTemplate.query("SELECT u FROM t_user u WHERE u.id > ?",
                        new UserMapper(), idMin);
    }
}
