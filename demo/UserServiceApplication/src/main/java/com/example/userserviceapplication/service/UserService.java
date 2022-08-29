package com.example.userserviceapplication.service;

import com.example.userserviceapplication.dto.UserDto;
import com.example.userserviceapplication.repository.UserEntity;

public interface UserService {
    UserDto createUser(UserDto userDto);
    UserDto getUserById(String userId);
    Iterable<UserEntity> getUserByAll();
}
