package com.example.userservice.proxy;



import com.example.userservice.config.security.FeignClientConfig;
import com.example.userservice.dto.KeycloakUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(name = "auth-service",configuration = FeignClientConfig.class)
public interface AuthProxy {


    @GetMapping("/auth/users/{userId}")
    KeycloakUser getUserById(@PathVariable String userId);
}
