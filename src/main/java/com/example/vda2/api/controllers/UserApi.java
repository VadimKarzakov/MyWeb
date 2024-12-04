package com.example.vda2.api.controllers;

import com.example.vda2.api.dto.UserDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "Пользователи") // Добавим описание для группы API
@RequestMapping("/users")
public interface UserApi {

    @ApiOperation(value = "Создать пользователя", notes = "Создает нового пользователя в системе")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Пользователь создан"),
            @ApiResponse(code = 400, message = "Неверные данные пользователя")
    })
    @PostMapping
    ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto);

    @ApiOperation(value = "Получить всех пользователей", notes = "Получает список всех пользователей в системе")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Список пользователей получен"),
            @ApiResponse(code = 404, message = "Пользователи не найдены")
    })
    @GetMapping
    ResponseEntity<List<UserDto>> getAllUsers();
}
