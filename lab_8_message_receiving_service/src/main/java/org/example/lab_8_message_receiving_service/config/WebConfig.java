package org.example.lab_8_message_receiving_service.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Класс конфигурации веб-приложения
 */
@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {
}
