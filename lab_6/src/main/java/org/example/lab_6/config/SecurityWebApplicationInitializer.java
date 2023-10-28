package org.example.lab_6.config;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/**
 * Инициализатор веб-приложения для безопасности.
 * Этот класс наследуется от AbstractSecurityWebApplicationInitializer,
 * что автоматически регистрирует springSecurityFilterChain Filter для каждого URL в вашем приложении.
 */
public class SecurityWebApplicationInitializer extends
        AbstractSecurityWebApplicationInitializer {
}