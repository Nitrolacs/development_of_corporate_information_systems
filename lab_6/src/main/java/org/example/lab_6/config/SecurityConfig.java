package org.example.lab_6.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.example.lab_6.service.UserService;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * Конфигурация безопасности веб-приложения.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    UserService userService;

    /**
     * Конфигурация сервиса аутентификации.
     * @param auth объект для построения аутентификации
     * @throws Exception если произошла ошибка во время аутентификации
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder());
    }

    /**
     * Конфигурация безопасности HTTP.
     * @param http объект для настройки безопасности HTTP
     * @throws Exception если произошла ошибка во время настройки безопасности HTTP
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/login", "/registration").permitAll()
                .antMatchers(HttpMethod.POST, "/bicycles").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/bicycles/{id}").hasRole("ADMIN")
                .antMatchers("/bicycles/new", "/bicycles/{id}/edit").hasRole("ADMIN")
                .antMatchers("/", "/bicycles", "/bicycles/{id}", "/bicycles/criterion").hasRole("USER")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/")
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login")
                .permitAll()
                .and()
                .httpBasic();
    }

    /**
     * Создание кодировщика паролей.
     * @return новый экземпляр кодировщика паролей BCryptPasswordEncoder
     */
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
