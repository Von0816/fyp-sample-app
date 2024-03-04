package com.example.userservice.service;

import com.example.userservice.dto.KeycloakUser;
import com.example.userservice.dto.UserRegistrationRequestRecord;
import com.example.userservice.dto.UserUpdateRecord;
import com.example.userservice.model.User;
import com.example.userservice.proxy.AuthProxy;
import com.example.userservice.proxy.MusicProxy;
import com.example.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

import static java.util.Objects.nonNull;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements  UserService{

    private  final UserRepository userRepository;

    private  final MusicProxy musicProxy;
    private  final AuthProxy authProxy;
    @Override
    public User save(User user) {

        User save = userRepository.save(user);
        // createMusicUser(save);
        return save;
    }

    private void createMusicUser(User user) {

        KeycloakUser keycloakUser = authProxy.getUserById(user.getId());
        boolean artist = Objects.equals("ARTIST",user.getType());

        UserRegistrationRequestRecord userRegistrationRequestRecord = new UserRegistrationRequestRecord(
                artist,user.getId(),String.format("%s %s",keycloakUser.getFirstName(),keycloakUser.getLastName()),user.getDob(),user.getGender(),user.getLanguage(),user.getCountryIso2()
        );
        musicProxy.createNewUser(userRegistrationRequestRecord);

    }

    @Override
    public User update(User user, String userId) {

        User user1 = getById(userId);
        if(nonNull(user1)){
            user.setId(userId);
            updateMusicUser(user);
            return  userRepository.save(user);
        }
       throw new RuntimeException("USer does not exist for id "+userId);
    }

    private void updateMusicUser(User user) {

        KeycloakUser keycloakUser = authProxy.getUserById(user.getId());
        UserUpdateRecord userUpdateRecord=new UserUpdateRecord(String.format("%s %s",keycloakUser.getFirstName(),keycloakUser.getLastName()),user.getDob(),user.getGender(),user.getLanguage(),user.getCountryIso2());
        musicProxy.updateUser(userUpdateRecord,user.getId());
    }

    @Override
    public User getById(String userId) {

        return userRepository.findById(userId).orElse(null);
    }

    @Override
    public void deleteUserById(String userId) {
        userRepository.deleteById(userId);

    }

}
