package com.example.vda2.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "app_user")  // Указываем имя таблицы
@NoArgsConstructor  // Пустой конструктор для JPA
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("email")
    private String email;

    // Новое поле для фиксации получения уведомления
    @JsonProperty("notificationReceived")
    private boolean notificationReceived;

    // Конструктор с параметрами
    public User(String name, String email) {
        this.name = name;
        this.email = email;
        this.notificationReceived = false; // Изначально уведомление не получено
    }
}
