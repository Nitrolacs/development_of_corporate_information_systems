package org.example.lab_8.config;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Класс JmsConfig представляет собой конфигурацию для RabbitMQ.
 * Он помечен аннотацией @Configuration, что указывает на то, что этот класс содержит методы @Bean.
 */
@Configuration
public class JmsConfig {

    /**
     * Название очереди сообщений
     */
    static final String queueName = "bike-queue";

    /**
     * Этот метод создает новый объект RabbitTemplate.
     * Он также объявляет новую очередь с именем, определенным в поле queueName.
     * @return Возвращает новый объект RabbitTemplate.
     */
    @Bean
    RabbitTemplate rabbitTemplate() {
        CachingConnectionFactory connectionFactory =
                new CachingConnectionFactory("localhost", 5672);
        AmqpAdmin admin = new RabbitAdmin(connectionFactory);
        admin.declareQueue(new Queue(queueName));

        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());

        return rabbitTemplate;
    }
}
