package org.example.lab_6.models;

import org.springframework.security.core.GrantedAuthority;

import javax.validation.constraints.Size;
import java.util.Set;

/**
 * Класс, представляющий роль пользователя.
 * Этот класс реализует интерфейс GrantedAuthority для предоставления полномочий, связанных с ролью.
 */
public class Role implements GrantedAuthority {

    /**
     * Идентификатор
     */
    private Integer id;

    /**
     * Название роли
     */
    @Size(min = 2)
    private String name;

    /**
     * Сет пользователей
     */
    private Set<User> users;

    /**
     * Конструктор по-умолчанию.
     */
    public Role() {
    }

    /**
     * Конструктор с одним параметром.
     * @param id идентификатор роли
     */
    public Role(Integer id) {
        this.id = id;
    }

    /**
     * Конструктор с двумя параметрами
     * @param id идентификатор роли
     * @param name название роли
     */
    public Role(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Геттер для id
     * @return идентификатор роли
     */
    public Integer getId() {
        return id;
    }

    /**
     * Сеттер для идентификатора.
     * @param id идентификатор роли
     */
    public void setId(Integer id) {
        this.id = id;

    }
    /**
     * Геттер для имени.
     * @return имя роли
     */
    public String getName() {
        return name;
    }

    /**
     * Сеттер для имени.
     * @param name имя роли
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Геттер для пользователей.
     * @return набор пользователей с данной ролью
     */
    public Set<User> getUsers() {
        return users;
    }

    /**
     * Сеттер для пользователей.
     * @param users набор пользователей с данной ролью
     */
    public void setUsers(Set<User> users) {
        this.users = users;
    }

    /**
     * Метод для получения полномочий, связанных с ролью.
     * @return имя роли
     */
    @Override
    public String getAuthority() {
        return getName();
    }
}
