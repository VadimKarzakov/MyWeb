package com.example.vda2;

import com.example.vda2.model.OrderNotification;
import com.example.vda2.repository.OrderNotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private OrderNotificationRepository repository;

    @Override
    public void run(String... args) throws Exception {
        // Создаем тестовые уведомления
        OrderNotification notification1 = new OrderNotification();
        notification1.setMessage("Ваш заказ готов к выдаче");
        notification1.setStatus("готово");
        notification1.setLockerLocation("Постомат #123");

        OrderNotification notification2 = new OrderNotification();
        notification2.setMessage("Ваш заказ в процессе обработки");
        notification2.setStatus("в процессе");
        notification2.setLockerLocation("Постомат #456");

        OrderNotification notification3 = new OrderNotification();
        notification3.setMessage("Ваш заказ ожидает забора");
        notification3.setStatus("готово");
        notification3.setLockerLocation("Постомат #789");

        // Сохраняем уведомления в базе данных
        repository.save(notification1);
        repository.save(notification2);
        repository.save(notification3);

        System.out.println("Данные успешно загружены в базу данных.");
    }
}

