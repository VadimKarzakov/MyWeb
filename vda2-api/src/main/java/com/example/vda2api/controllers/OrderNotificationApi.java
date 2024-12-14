package com.example.vda2api.controllers;

import com.example.vda2api.dto.OrderNotificationDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RequestMapping("/api/notifications")
public interface OrderNotificationApi {

    @Operation(summary = "Получить уведомление по ID",
            description = "Получить уведомление по его ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Уведомление найдено"),
            @ApiResponse(responseCode = "404", description = "Уведомление не найдено")
    })
    @GetMapping("/{id}")
    ResponseEntity<OrderNotificationDto> getNotification(@PathVariable Long id);

    @Operation(summary = "Получить все уведомления",
            description = "Получить список всех уведомлений")
    @ApiResponse(responseCode = "200", description = "Уведомления получены")
    @GetMapping
    ResponseEntity<List<OrderNotificationDto>> getAllNotifications();

    @Operation(summary = "Создать новое уведомление",
            description = "Создать новое уведомление")
    @ApiResponse(responseCode = "200", description = "Уведомление создано")
    @PostMapping
    ResponseEntity<OrderNotificationDto> createNotification(@RequestBody OrderNotificationDto notificationDto);

    @Operation(summary = "Удалить уведомление по ID",
            description = "Удалить уведомление по его ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Уведомление удалено"),
            @ApiResponse(responseCode = "404", description = "Уведомление не найдено")
    })
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteNotification(@PathVariable Long id);
}
