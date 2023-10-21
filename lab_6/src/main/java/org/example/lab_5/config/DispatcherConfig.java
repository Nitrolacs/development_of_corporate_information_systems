package org.example.lab_5.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.example.lab_5.filter.RequestFilter;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Класс конфигурации диспетчера
 */
public class DispatcherConfig extends AbstractAnnotationConfigDispatcherServletInitializer {

    /**
     * Возвращает массив классов, которые содержат конфигурацию корневого контекста приложения.
     * @return AppConfig class
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{AppConfig.class, SecurityConfig.class};
    }

    /**
     * Возвращает массив классов, которые содержат конфигурацию контекста сервлета
     * @return WebConfig class
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{WebConfig.class};
    }

    /**
     * Возвращает массив строк, которые определяют шаблоны URL для диспетчера сервлетов.
     * @return возврашает строку "/", это значит что диспетчер будет обрабатывать все запросы.
     */
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    /**
     * Вызывается при запуске приложения
     * @param aServletContext Контекст сервлета
     * @throws ServletException
     */
    @Override
    public void onStartup(ServletContext aServletContext) throws ServletException {
        super.onStartup(aServletContext);
        registerRequestFilter(aServletContext);
        registerHiddenFieldFilter(aServletContext);
    }

    /**
     * Регистрирует фильтр RequestFilter(), используемый для логирования и обработки запросов.
     * @param aContext
     */
    private void registerRequestFilter(ServletContext aContext) {
        aContext.addFilter("requestFilter",
                new RequestFilter()).addMappingForUrlPatterns(null, true, "/*");
    }

    /**
     * Регистрирует фильтр HiddenHttpMethodFilter, который используется для
     * поддержки методов HTTP PUT, PATCH и DELETE.
     * @param aContext
     */
    private void registerHiddenFieldFilter(ServletContext aContext) {
        aContext.addFilter("hiddenHttpMethodFilter",
                new HiddenHttpMethodFilter()).addMappingForUrlPatterns(null, true, "/*");
    }
}
