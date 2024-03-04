package com.example.userservice.service;

import com.example.userservice.model.User;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {

    User save(User user);
    User update(User user, String userId);
    User getById(String userId);
    void deleteUserById(String userId);
}
