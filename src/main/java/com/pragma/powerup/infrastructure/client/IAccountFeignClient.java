package com.pragma.powerup.infrastructure.client;

import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
@Headers("Content-Type: application/json")
@FeignClient(name ="users-microservice", url="localhost:8081")
public interface IAccountFeignClient {

    @GetMapping(value = "/api/v1/account/role")
    Long getUserRole(@RequestHeader("Authorization") String authHeader);

    @GetMapping(value = "/api/v1/account/roleAndId")
    Long[] getUserIdAndRole(@RequestHeader("Authorization") String authHeader);
}
