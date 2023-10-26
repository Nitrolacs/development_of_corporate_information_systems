package org.example.lab_6.filter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpFilter;
import java.io.IOException;

/**
 * Класс фильтра запросов
 */
public class RequestFilter extends HttpFilter {

    /**
     * Переопределяет метод суперкласса и используется для фильтрации запросов и ответов.
     * @param request входящий запрос к сервлету
     * @param response исходящий ответ от сервлета
     * @param chain цепочка фильтров
     * @throws IOException исключение
     * @throws ServletException исключение
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        chain.doFilter(request, response);
    }
}
