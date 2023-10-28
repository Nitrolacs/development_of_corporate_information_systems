package org.example.lab_6.models;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.GrantedAuthority;

import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Set;

public class User implements UserDetails {

    /**
     * Идентификатор
     */
    private Integer id;

    /**
     * Имя/логин пользователя
     */
    @Size(min = 4, max = 300, message = "От 5 до 300 знаков")
    private String username;

    /**
     * Пароль
     */
    @Size(min = 4, max = 300, message = "От 5 до 300 знаков")
    private String password;

    /**
     * Подтверждение пароля
     */
    private String passwordConfirm;

    /**
     * Все роли
     */
    private Set<Role> roles;

    /**
     * Конструктор по умолчанию.
     */
    public User() {
    }

    /**
     * Геттер для идентификатора.
     *
     * @return идентификатор пользователя
     */
    public Integer getId() {
        return id;
    }

    /**
     * Сеттер для идентификатора.
     *
     * @param id идентификатор пользователя
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Геттер для имени пользователя.
     *
     * @return имя пользователя
     */
    @Override
    public String getUsername() {
        return username;
    }

    /**
     * Метод для проверки, истек ли срок действия учетной записи пользователя.
     * @return true, если срок действия учетной записи не истек, false - в противном случае
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Метод для проверки, заблокирована ли учетная запись пользователя.
     * @return true, если учетная запись не заблокирована, false - в противном случае
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Метод для проверки, истек ли срок действия учетных данных пользователя.
     * @return true, если срок действия учетных данных не истек, false - в противном случае
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Метод для проверки, включена ли учетная запись пользователя.
     * @return true, если учетная запись включена, false - в противном случае
     */
    @Override
    public boolean isEnabled() {
        return true;
    }

    /**
     * Сеттер для имени пользователя.
     * @param username имя пользователя
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Метод для получения полномочий пользователя.
     * @return коллекция полномочий пользователя
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    /**
     * Геттер для пароля.
     * @return пароль пользователя
     */
    @Override
    public String getPassword() {
        return password;
    }

    /**
     * Сеттер для пароля.
     * @param password пароль пользователя
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Геттер для подтверждения пароля.
     * @return подтверждение пароля
     */
    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    /**
     * Сеттер для подтверждения пароля.
     * @param passwordConfirm подтверждение пароля
     */
    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    /**
     * Геттер для ролей пользователя.
     * @return набор ролей пользователя
     */
    public Set<Role> getRoles() {
        return roles;
    }

    /**
     * Сеттер для ролей пользователя.
     * @param roles набор ролей пользователя
     */
    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
