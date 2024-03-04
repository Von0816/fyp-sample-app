package com.example.userservice.proxy;



import com.example.userservice.config.security.FeignClientConfig;
import com.example.userservice.dto.UserRegistrationRequestRecord;
import com.example.userservice.dto.UserUpdateRecord;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Component
@FeignClient(name = "music-service",configuration = FeignClientConfig.class)
public interface MusicProxy {


    @PostMapping("/music/users")
    UserRegistrationRequestRecord createNewUser(@RequestBody UserRegistrationRequestRecord request);
    @PutMapping("/music/users/{userId}")
    UserUpdateRecord updateUser(@RequestBody UserUpdateRecord request, @PathVariable String userId);
}
