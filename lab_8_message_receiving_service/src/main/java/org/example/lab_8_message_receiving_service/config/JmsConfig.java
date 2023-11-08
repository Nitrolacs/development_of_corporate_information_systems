package org.example.lab_8_message_receiving_service.config;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;

/**
 * Класс JmsConfig предоставляет конфигурацию для RabbitMQ.
 * Он реализует интерфейс RabbitListenerConfigurer для настройки Rabbit Listener.
 */
@Configuration
@ComponentScan("org.example.lab_8_message_receiving_service.receiver")
@EnableRabbit
public class JmsConfig implements RabbitListenerConfigurer {

    /**
     * Этот метод используется для настройки Rabbit Listener.
     * @param registrar Регистратор конечных точек прослушивателя Rabbit.
     */
    @Override
    public void configureRabbitListeners(RabbitListenerEndpointRegistrar registrar) {
        registrar.setMessageHandlerMethodFactory(myHandlerMethodFactory());
    }

    /**
     * Этот метод создает новый объект CachingConnectionFactory.
     * @return Возвращает новый объект CachingConnectionFactory.
     */
    @Bean
    CachingConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new
                CachingConnectionFactory("localhost", 5672);
        return connectionFactory;
    }

    /**
     * Этот метод создает новый объект SimpleRabbitListenerContainerFactory.
     * @return Возвращает новый объект SimpleRabbitListenerContainerFactory.
     */
    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory() {
        SimpleRabbitListenerContainerFactory factory =
                new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory());
        factory.setBatchListener(true);
        return factory;
    }

    /**
     * Этот метод создает новый объект DefaultMessageHandlerMethodFactory.
     * @return Возвращает новый объект DefaultMessageHandlerMethodFactory.
     */
    @Bean
    public DefaultMessageHandlerMethodFactory myHandlerMethodFactory() {
        DefaultMessageHandlerMethodFactory factory =
                new DefaultMessageHandlerMethodFactory();
        factory.setMessageConverter(new MappingJackson2MessageConverter());
        return factory;
    }
}
