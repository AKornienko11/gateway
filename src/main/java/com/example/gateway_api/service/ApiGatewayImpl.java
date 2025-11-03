package com.example.gateway_api.service;

import com.example.gateway_api.dto.UserDTO;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class ApiGatewayImpl implements ApiGatewayService {

    private final RestTemplate restTemplate;

    private HttpHeaders headers = new HttpHeaders();

    public ApiGatewayImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public String getUser(Long id) {

//        addParamForHeaders();
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();

        HttpEntity<?> requestEntity = new HttpEntity<>(body, headers);

        // Перенаправляем запрос на нужный микросервис
        return this.restTemplate.exchange(
                        "http://localhost:8083/api/user/" + id,
                        org.springframework.http.HttpMethod.GET,
                        requestEntity,
                        String.class)
                .getBody();
    }



    @Override
    public String createUser(UserDTO updatedUser) {
        addParamForHeaders();
        HttpEntity<UserDTO> requestEntity = new HttpEntity<>(updatedUser);
        return restTemplate.exchange(
                "http://localhost:8083/api/user/create",
                HttpMethod.POST,
                requestEntity,
                String.class).getBody();
    }

    @Override
    public String deleteUser(Long id) {
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();

        HttpEntity<?> requestEntity = new HttpEntity<>(body, headers);

        // Перенаправляем запрос на нужный микросервис
        return this.restTemplate.exchange(
                        "http://localhost:8083/api/user/delete/" + id,
                        HttpMethod.DELETE,
                        requestEntity,
                        String.class)
                .getBody();
    }

    @Override
    public String updateUser(Long id, UserDTO updatedUser) {
        addParamForHeaders();

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();

        HttpEntity<?> requestEntity = new HttpEntity<>(body, headers);
        return restTemplate.exchange(
                "http://localhost:8083/api/user/update/" + id,
                HttpMethod.PUT,
                requestEntity,
                String.class).getBody();
    }


    private void addParamForHeaders() {
        headers.setContentType(MediaType.APPLICATION_JSON);
    }
}
