package com.example.gateway_api.controller;

import com.example.gateway_api.dto.UserDTO;
import com.example.gateway_api.dto.emailrequest.EmailRequest;
import com.example.gateway_api.service.ApiGatewayService;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/")
public class ApiGatewayController {

    private final ApiGatewayService apiGateway;


    public ApiGatewayController(ApiGatewayService apiGateway) {
        this.apiGateway = apiGateway;
    }


    @GetMapping("/get/{id}")

    public UserDTO getUser(@PathVariable Long id) {
        return apiGateway.getUser(id);
    }


    @PostMapping("/create")
    public String createUser(@RequestBody UserDTO updatedUser) {
        return apiGateway.createUser(updatedUser);
    }

    @PutMapping("/update")
    public String updateUser(@RequestBody UserDTO updatedUser) {
        return apiGateway.updateUser(updatedUser);
    }


    @DeleteMapping("/delete/{id}")

    public String deleteUser(@PathVariable Long id) {
        return apiGateway.deleteUser(id);
    }

    @PostMapping("/send")
    public String createUser(@RequestBody EmailRequest request) {
        return apiGateway.send(request);
    }


}