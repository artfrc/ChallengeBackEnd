package com.challengebackend.challengebackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ExternalServiceCaller {
    
    @Autowired
    private RestTemplate restTemplate;

    public String callService(String name) {
        String uri = "http://172.31.230.177:8080/chame-aqui/" + name;
        return restTemplate.getForObject(uri, String.class);
    }

}
