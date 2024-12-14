package com.example.vda2.controller;

import com.example.vda2api.exception.ResourceNotFoundException;
import com.example.vda2api.controllers.UserApi;

import com.example.vda2api.dto.UserDto;





import com.example.vda2.model.OrderNotification;
import com.example.vda2.model.User;
import com.example.vda2.repository.UserRepository;
import com.example.vda2.service.MessageSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UserController implements UserApi {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MessageSenderService messageSenderService;

    @Override
    public ResponseEntity<UserDto> createUser(UserDto userDto) {
        // Конвертируем DTO в сущность
        User user = new User(userDto.name(), userDto.email());
        // Сохраняем пользователя
        User savedUser = userRepository.save(user);
        // Возвращаем сохраненного пользователя как DTO
        return ResponseEntity.ok(convertToDto(savedUser));
    }

    @Override
    public ResponseEntity<List<UserDto>> getAllUsers() {
        // Получаем список всех пользователей из базы
        List<User> users = userRepository.findAll();
        // Конвертируем в DTO и возвращаем
        return ResponseEntity.ok(users.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList()));
    }

    /**
     * Метод для уведомления пользователя (вне интерфейса UserApi).
     */
    public ResponseEntity<String> notifyUser(Long userId, String lockerLocation, String status) {
        // Проверяем существование пользователя
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Пользователь с ID " + userId + " не найден"));

        // Создаем уведомление
        String message = "Ваш заказ готов и ждет вас в постомате";
        OrderNotification notification = new OrderNotification(message, status, lockerLocation, user.getId());

        // Отправляем уведомление через RabbitMQ
        messageSenderService.sendNotification(notification);

        return ResponseEntity.ok("Уведомление отправлено пользователю с ID " + userId);
    }

    /**
     * Вспомогательный метод для конвертации модели в DTO.
     */
    private UserDto convertToDto(User user) {
        return new UserDto(user.getName(), user.getEmail());
    }
}
