package com.example.gateway_api.service;

import com.example.gateway_api.dto.UserDTO;
import com.example.gateway_api.dto.emailrequest.EmailRequest;

public interface ApiGatewayService {

    public UserDTO getUser(Long id);
    public String createUser(UserDTO updatedUser);

    public String deleteUser(Long id);

    String updateUser(UserDTO updatedUser);


    String send(EmailRequest request);
}
