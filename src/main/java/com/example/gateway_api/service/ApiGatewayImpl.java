package com.example.gateway_api.service;

import com.example.gateway_api.controller.ApiGatewayController;
import com.example.gateway_api.dto.UserDTO;
import com.example.gateway_api.dto.emailrequest.EmailRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ApiGatewayImpl implements ApiGatewayService {

    private final RestTemplate restTemplate;
    private static final Logger log = LoggerFactory.getLogger(ApiGatewayController.class);

    private HttpHeaders headers = new HttpHeaders();

    public ApiGatewayImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public UserDTO getUser(Long id) {
        HttpEntity<?> requestEntity = new HttpEntity<>(null);

        try {
            UserDTO userDTO = this.restTemplate.exchange(
                            "http://localhost:8098/delivery/get/" + id,
                            HttpMethod.GET,
                            requestEntity,
                            UserDTO.class)
                    .getBody();
            return userDTO;

        } catch (Throwable e) {
            log.error("Ошибка получения пользователя с ID={}, причина: {}", id, e.getMessage());

        }
        return new UserDTO();
    }


    @Override
    public String createUser(UserDTO updatedUser) {
        HttpEntity<UserDTO> requestEntity = new HttpEntity<>(updatedUser);

        try {
            String result = restTemplate.exchange(
                    "http://localhost:8098/delivery/create",
                    HttpMethod.POST,
                    requestEntity,
                    String.class).getBody();
            return result;

        } catch (Throwable e) {
            log.error("Ошибка создания пользователя , причина: {}", e.getMessage());

        }

        return "В данный момент сервер не доступен ";
    }

    @Override
    public String deleteUser(Long id) {
        HttpEntity<?> requestEntity = new HttpEntity<>(null);
        try {
            String result = this.restTemplate.exchange(
                            "http://localhost:8098/delivery/delete/" + id,
                            HttpMethod.DELETE,
                            requestEntity,
                            String.class)
                    .getBody();
            return result;
        } catch (Throwable e) {
            log.error("Ошибка удаления  пользователя , причина: {}", e.getMessage());
        }
        return "В данный момент сервер не доступен ";
    }

    @Override
    public String updateUser(UserDTO updatedUser) {
        addParamForHeaders();
        HttpEntity<UserDTO> requestEntity = new HttpEntity<>(updatedUser);
        try {
            String result = restTemplate.exchange(
                    "http://localhost:8098/delivery/update",
                    HttpMethod.PUT,
                    requestEntity,
                    String.class).getBody();
            return result;
        } catch (Throwable e) {
            log.error("Ошибка обновления пользователя , причина: {}", e.getMessage());
        }
        return "В данный момент сервер не доступен ";
    }

    @Override
    public String send(EmailRequest request) {
        addParamForHeaders();
        HttpEntity<EmailRequest> requestEntity = new HttpEntity<>(request);
        try {
            String result = restTemplate.exchange(
                    "http://localhost:8098/delivery/send",
                    HttpMethod.POST,
                    requestEntity,
                    String.class).getBody();
            return result;
        } catch (Throwable e) {
            log.error("Ошибка отправки письма  пользователю , причина: {}", e.getMessage());
        }
        return "В данный момент сервер не доступен ";
    }


    private void addParamForHeaders() {
        headers.setContentType(MediaType.APPLICATION_JSON);
    }
}
