package org.example.lab_8_message_receiving_service.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Класс конфигурации диспетчера
 */
public class DispatcherConfig extends
        AbstractAnnotationConfigDispatcherServletInitializer {

    /**
     * Возвращает массив классов, которые содержат конфигурацию корневого контекста приложения.
     * @return JmsConfig class
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{JmsConfig.class};
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
}