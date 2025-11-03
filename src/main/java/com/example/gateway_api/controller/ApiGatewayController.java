package com.example.gateway_api.controller;

import com.example.gateway_api.dto.UserDTO;
import com.example.gateway_api.service.ApiGatewayService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/")
public class ApiGatewayController {

    private final ApiGatewayService apiGateway;

    public ApiGatewayController(ApiGatewayService apiGateway) {
        this.apiGateway = apiGateway;
    }

    // Простой метод для примера переадресации GET-запросов
    @GetMapping("/users/{id}")
    public String getUser(@PathVariable Long id) {
        return apiGateway.getUser(id);

    }



    @PostMapping("/create")
    public ResponseEntity<String> updateUser(@RequestBody UserDTO updatedUser) {
        String result = apiGateway.createUser(updatedUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Long id, @RequestBody UserDTO updatedUser) {
        String result = apiGateway.updateUser(id, updatedUser);
        return ResponseEntity.status(HttpStatus.OK).body(result);

    }

    @DeleteMapping("/delete/{id}")
    public  ResponseEntity<String> deleteUser(@PathVariable Long id) {
        String result =  apiGateway.deleteUser(id);
        return ResponseEntity.status(HttpStatus.OK).body(result);

    }

}