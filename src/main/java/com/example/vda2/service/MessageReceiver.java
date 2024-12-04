package com.example.vda2.service;

import com.example.vda2.model.OrderNotification;
import com.example.vda2.model.User;
import com.example.vda2.repository.UserRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageReceiver {

    @Autowired
    private UserRepository userRepository;

    @RabbitListener(queues = "${queue.name}")
    public void receiveMessage(OrderNotification notification) {
        Long userId = notification.getUserId();  // Получаем userId из уведомления
        User user = userRepository.findById(userId).orElse(null);

        if (user != null) {
            user.setNotificationReceived(true);  // Обновляем статус уведомления для пользователя
            userRepository.save(user);
            System.out.println("Уведомление обработано для пользователя: " + user.getName());
        }
    }
}
