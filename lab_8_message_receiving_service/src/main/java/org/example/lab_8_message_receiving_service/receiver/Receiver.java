package org.example.lab_8_message_receiving_service.receiver;

import org.example.lab_8_message_receiving_service.model.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Класс Receiver представляет собой компонент, который слушает сообщения из очереди "bike-queue".
 */
@Component
public class Receiver {

    /**
     * Этот метод слушает сообщения из очереди "bike-queue".
     * При получении сообщения он выводит его содержимое в консоль.
     * @param message Сообщение, полученное из очереди.
     */
    @RabbitListener(queues = "bike-queue",
            containerFactory = "rabbitListenerContainerFactory")
    public void listen(Message message) {
        System.out.println("Вам пришло следующее сообщение:");
        System.out.println(message.getMessage() + message.getBike());
    }
}