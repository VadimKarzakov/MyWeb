package com.example.vda2.graphql;

import com.example.vda2.model.OrderNotification;
import com.example.vda2.service.OrderNotificationService;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsQuery;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@DgsComponent
public class OrderNotificationQuery {

    @Autowired
    private OrderNotificationService service;

    // Запрос для получения уведомления по ID
    @DgsQuery
    public OrderNotification getNotification(Long id) {
        return service.getNotificationById(id)
                .orElseThrow(() -> new RuntimeException("Notification with id " + id + " not found"));
    }

    // Запрос для получения всех уведомлений
    @DgsQuery
    public List<OrderNotification> getAllNotifications() {
        return service.getAllNotifications();
    }
}
