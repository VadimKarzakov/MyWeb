package com.example.vda2api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record OrderNotificationDto(
        Long id,
        @NotBlank(message = "Сообщение не может быть пустым") String message,
        @NotBlank(message = "Статус обязателен") String status,
        @NotBlank(message = "Локация постомата обязательна") String lockerLocation,
        @NotNull(message = "ID пользователя обязателен") Long userId
) {
}
