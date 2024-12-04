package com.example.vda2.service;

import com.example.vda2.model.OrderNotification;
import com.example.vda2.repository.OrderNotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderNotificationService {

    @Autowired
    private OrderNotificationRepository repository;

    public Optional<OrderNotification> getNotificationById(Long id) {
        return repository.findById(id); // findById возвращает Optional
    }

    public List<OrderNotification> getAllNotifications() {
        return repository.findAll();
    }

    public OrderNotification saveNotification(OrderNotification notification) {
        return repository.save(notification);
    }

    public void deleteNotification(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Notification with id " + id + " not found");
        }
        repository.deleteById(id);
    }
}
