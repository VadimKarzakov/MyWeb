package com.example.vda2.api.controllers;

import com.example.vda2.api.dto.OrderNotificationDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/notifications")
public interface OrderNotificationApi {

    @ApiOperation(value = "Получить уведомление по ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Уведомление найдено"),
            @ApiResponse(code = 404, message = "Уведомление не найдено")
    })
    @GetMapping("/{id}")
    ResponseEntity<OrderNotificationDto> getNotification(@PathVariable Long id);

    @ApiOperation(value = "Получить все уведомления")
    @ApiResponse(code = 200, message = "Уведомления получены")
    @GetMapping
    ResponseEntity<List<OrderNotificationDto>> getAllNotifications();

    @ApiOperation(value = "Создать новое уведомление")
    @ApiResponse(code = 200, message = "Уведомление создано")
    @PostMapping
    ResponseEntity<OrderNotificationDto> createNotification(@RequestBody OrderNotificationDto notificationDto);

    @ApiOperation(value = "Удалить уведомление по ID")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Уведомление удалено"),
            @ApiResponse(code = 404, message = "Уведомление не найдено")
    })
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteNotification(@PathVariable Long id);
}
