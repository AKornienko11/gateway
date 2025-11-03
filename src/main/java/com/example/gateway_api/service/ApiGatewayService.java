package com.example.gateway_api.service;

import com.example.gateway_api.dto.UserDTO;

public interface ApiGatewayService {

    public String getUser(Long id);
    public String createUser(UserDTO updatedUser);

    public String deleteUser(Long id);


    String updateUser(Long id, UserDTO updatedUser);
}
