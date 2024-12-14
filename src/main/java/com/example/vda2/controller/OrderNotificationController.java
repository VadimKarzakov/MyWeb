package com.example.vda2.controller;


import com.example.vda2api.controllers.OrderNotificationApi;
import com.example.vda2api.dto.OrderNotificationDto;
import com.example.vda2api.exception.ResourceNotFoundException;


import com.example.vda2.model.OrderNotification;
import com.example.vda2.service.OrderNotificationService;
import com.example.vda2.service.MessageSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class OrderNotificationController implements OrderNotificationApi {

    @Autowired
    private OrderNotificationService service;

    @Autowired
    private MessageSenderService messageSenderService;

    @Override
    public ResponseEntity<OrderNotificationDto> getNotification(Long id) {
        OrderNotification notification = service.getNotificationById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Уведомление с ID " + id + " не найдено"));

        return ResponseEntity.ok(convertToDto(notification));
    }

    @Override
    public ResponseEntity<List<OrderNotificationDto>> getAllNotifications() {
        return ResponseEntity.ok(service.getAllNotifications()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList()));
    }

    @Override
    public ResponseEntity<OrderNotificationDto> createNotification(OrderNotificationDto notificationDto) {
        OrderNotification notification = service.saveNotification(convertToEntity(notificationDto));
        messageSenderService.sendNotification(notification);
        return ResponseEntity.ok(convertToDto(notification));
    }

    @Override
    public ResponseEntity<Void> deleteNotification(Long id) {
        service.getNotificationById(id).orElseThrow(() ->
                new ResourceNotFoundException("Уведомление с ID " + id + " не найдено"));
        service.deleteNotification(id);
        return ResponseEntity.noContent().build();
    }

    private OrderNotificationDto convertToDto(OrderNotification notification) {
        return new OrderNotificationDto(notification.getId(), notification.getMessage(),
                notification.getStatus(), notification.getLockerLocation(), notification.getUserId());
    }

    private OrderNotification convertToEntity(OrderNotificationDto dto) {
        return new OrderNotification(dto.message(), dto.status(), dto.lockerLocation(), dto.userId());
    }
}
