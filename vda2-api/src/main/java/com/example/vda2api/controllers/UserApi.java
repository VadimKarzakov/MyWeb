package com.example.vda2api.controllers;

import com.example.vda2api.dto.UserDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/users")
public interface UserApi {

    @Operation(summary = "Создать пользователя",
            description = "Создает нового пользователя в системе")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Пользователь создан"),
            @ApiResponse(responseCode = "400", description = "Неверные данные пользователя")
    })
    @PostMapping
    ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto);

    @Operation(summary = "Получить всех пользователей",
            description = "Получить список всех пользователей в системе")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Список пользователей получен"),
            @ApiResponse(responseCode = "404", description = "Пользователи не найдены")
    })
    @GetMapping
    ResponseEntity<List<UserDto>> getAllUsers();
}
