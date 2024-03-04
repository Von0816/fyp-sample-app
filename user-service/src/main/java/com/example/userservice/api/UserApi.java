package com.example.userservice.api;

import com.example.userservice.dto.UserDto;
// import com.example.userservice.mapper.DtoMapper;
import com.example.userservice.service.UserService;
import com.example.userservice.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RestController
@RequestMapping
public class UserApi {


    private  final UserService userService;
    // private  final DtoMapper mapper;

    @PostMapping("/users")
    public ResponseEntity<User>  saveUser(@RequestBody User user){

        userService.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @GetMapping("/users/{id}")
    public ResponseEntity<User>  getUserById(@PathVariable String id){

        return ResponseEntity.ok((userService.getById(id)));
    }
    @DeleteMapping("/users/{id}")
    @ResponseBody
    public void  deleteUserById(@PathVariable String id){

       userService.deleteUserById(id);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User>  updateUser(@PathVariable String id,@RequestBody User user){

        userService.update(user,id);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
