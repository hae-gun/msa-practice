package com.example.userserviceapplication.repository;

import com.example.userserviceapplication.dto.UserDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, Long> {
    UserEntity findByUserId(String userId);
    UserEntity findByEmail(String username);
}
