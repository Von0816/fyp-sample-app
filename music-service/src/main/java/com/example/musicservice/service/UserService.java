package com.example.musicservice.service;

import com.example.musicservice.dto.UserRegistrationRequestRecord;
import com.example.musicservice.model.User;

public interface UserService {

    User createUser(UserRegistrationRequestRecord user);
    void deleteByUserId(String id);
    void userFollowArtist(String userId,String artistId);
    void userUnFollowArtist(String userId,String artistId);
}
