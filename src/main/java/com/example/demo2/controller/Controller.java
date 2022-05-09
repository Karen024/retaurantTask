package com.example.demo2.controller;

import com.example.demo2.dto.request.AppUserRequestDto;
import com.example.demo2.dto.request.MealOrderRequestDto;
import com.example.demo2.dto.request.TableRequestDto;
import com.example.demo2.dto.response.AppUserResponseDto;
import com.example.demo2.dto.response.MealDto;
import com.example.demo2.dto.response.MealOrderResponseDto;
import com.example.demo2.dto.response.TableResponseDto;
import com.example.demo2.service.MealOrderService;
import com.example.demo2.service.MealService;
import com.example.demo2.service.TableService;
import com.example.demo2.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/restaurant")
public class Controller {
    private final MealService mealService;
    private final TableService tableService;
    private final MealOrderService mealOrderService;
    private final UserService userService;

    @PostMapping("/user/register")
    ResponseEntity<AppUserResponseDto> registerUser(@RequestBody AppUserRequestDto appUserRequestDto) {
        AppUserResponseDto response = userService.registerUser(appUserRequestDto);

        return ResponseEntity.ok(response);
    }

    @PostMapping(value = "/table/book")
    ResponseEntity<TableResponseDto> bookTable(@RequestBody TableRequestDto tableRequestDto) {
        TableResponseDto response = tableService.bookTable(tableRequestDto);

        return ResponseEntity.ok(response);
    }


    @PostMapping(value = "/users/{userId}/order")
    ResponseEntity<MealOrderResponseDto> orderMeals(@RequestBody MealOrderRequestDto mealOrderRequestDto, @PathVariable UUID userId) {
        MealOrderResponseDto response = mealOrderService.orderMeals(mealOrderRequestDto, userId);

        return ResponseEntity.ok(response);
    }

    @PostMapping(value = "/menu/csv")
    ResponseEntity<List<MealDto>> uploadMenu(@RequestBody MultipartFile file) {
        List<MealDto> response = mealService.uploadFromCsv(file);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/users")
    ResponseEntity<List<AppUserResponseDto>> getAllUsers() {
        List<AppUserResponseDto> response = userService.getAllUsers();

        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/menu")
    ResponseEntity<List<MealDto>> getMeals() {
        List<MealDto> response = mealService.getMenu();

        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/tables")
    ResponseEntity<List<TableResponseDto>> getTables() {
        List<TableResponseDto> response = tableService.getFreeTables();

        return ResponseEntity.ok(response);
    }

    @PutMapping(value = "/users/{userId}")
    ResponseEntity<AppUserResponseDto> updateUserInfo(@RequestBody AppUserRequestDto appUserRequestDto, @PathVariable UUID userId) {
        AppUserResponseDto response = userService.updateUserInfo(appUserRequestDto, userId);

        return ResponseEntity.ok(response);
    }

    @PutMapping(value = "/users/{userId}/order")
    ResponseEntity<MealOrderResponseDto> updateUserOrder(@RequestBody MealOrderRequestDto mealOrderRequestDto, @PathVariable UUID userId) {
        MealOrderResponseDto response = mealOrderService.updateMealOrder(mealOrderRequestDto, userId);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping(value = "/users/{userId}/book/cancel")
    ResponseEntity<TableResponseDto> cancelBook(@PathVariable UUID userId) {
        TableResponseDto response = tableService.cancelBook(userId);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping(value = "/users/{userId}/order/cancel")
    ResponseEntity<Integer> cancelOrder(@PathVariable UUID userId) {
        Integer response = mealOrderService.cancelOrder(userId);

        return ResponseEntity.ok(response);
    }
}
