package com.example.userserviceapplication.service;

import com.example.userserviceapplication.dto.UserDto;
import com.example.userserviceapplication.repository.UserEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    UserDto createUser(UserDto userDto);
    UserDto getUserByUserId(String userId);
    Iterable<UserEntity> getUserByAll();

    UserDto getUserDetailsByEmail(String userName);
}
