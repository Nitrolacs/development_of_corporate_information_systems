package org.example.lab_8_message_receiving_service.receiver;

import org.example.lab_8_message_receiving_service.model.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class Receiver {
    @RabbitListener(queues = "bike-queue",
            containerFactory = "rabbitListenerContainerFactory")
    public void listen(Message message) {
        System.out.println("ых");
    }
}