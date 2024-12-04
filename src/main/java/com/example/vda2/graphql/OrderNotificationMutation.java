package com.example.vda2.graphql;

import com.example.vda2.model.OrderNotification;
import com.example.vda2.service.OrderNotificationService;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsMutation;
import org.springframework.beans.factory.annotation.Autowired;

@DgsComponent
public class OrderNotificationMutation {

    @Autowired
    private OrderNotificationService service;

    // Мутация для создания уведомления
    @DgsMutation
    public OrderNotification createNotification(String message, String status, String lockerLocation) {
        OrderNotification notification = new OrderNotification();
        notification.setMessage(message);
        notification.setStatus(status);
        notification.setLockerLocation(lockerLocation);
        return service.saveNotification(notification);
    }

    // Мутация для удаления уведомления
    @DgsMutation
    public Boolean deleteNotification(Long id) {
        try {
            service.deleteNotification(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // Мутация для обновления уведомления
// Мутация для обновления уведомления
    @DgsMutation
    public OrderNotification updateNotification(Long id, String message, String status, String lockerLocation) {
        // Находим уведомление по id
        OrderNotification notification = service.getNotificationById(id)
                .orElseThrow(() -> new RuntimeException("Notification with id " + id + " not found"));

        // Обновляем поля, если они были переданы
        if (message != null) {
            notification.setMessage(message);
        }
        if (status != null) {
            notification.setStatus(status);
        }
        if (lockerLocation != null) {
            notification.setLockerLocation(lockerLocation);
        }

        // Сохраняем изменения
        return service.saveNotification(notification);
    }
}
