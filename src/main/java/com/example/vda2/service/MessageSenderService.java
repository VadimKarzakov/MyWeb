package com.example.vda2.service;

import com.example.vda2.model.OrderNotification;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MessageSenderService {
    @Value("${queue.name}")
    private String queueName;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    // Убедитесь, что этот метод существует
    public void sendNotification(OrderNotification notification) {
        rabbitTemplate.convertAndSend(queueName, notification);
        rabbitTemplate.convertAndSend(queueName, notification);
    }
}
