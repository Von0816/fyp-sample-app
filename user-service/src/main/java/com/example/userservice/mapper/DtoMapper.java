package com.example.userservice.mapper;

import com.example.userservice.dto.UserDto;
import com.example.userservice.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class DtoMapper {


    public abstract  User map(UserDto userDto);
    public abstract  UserDto map(User user);
}
