package com.example.vda2.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor  // Автоматически генерирует пустой конструктор
public class OrderNotification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("message")
    private String message;

    @JsonProperty("status")
    private String status;

    @JsonProperty("lockerLocation")
    private String lockerLocation;

    @JsonProperty("userId")  // Добавляем поле userId для связи с пользователем
    private Long userId;

    // Конструктор для всех параметров
    public OrderNotification(String message, String status, String lockerLocation, Long userId) {
        this.message = message;
        this.status = status;
        this.lockerLocation = lockerLocation;
        this.userId = userId;
    }

    // Конструктор для трех параметров
    public OrderNotification(String message, String status, String lockerLocation) {
        this.message = message;
        this.status = status;
        this.lockerLocation = lockerLocation;
    }

    // Конструктор для двух параметров
    public OrderNotification(String message, String status) {
        this.message = message;
        this.status = status;
        this.lockerLocation = "По умолчанию";  // Значение по умолчанию для локера
    }
}
