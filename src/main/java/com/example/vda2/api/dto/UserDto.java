package com.example.vda2.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserDto(
        @NotBlank(message = "Имя не может быть пустым") String name,
        @Email(message = "Неверный формат email") String email
) {
}
