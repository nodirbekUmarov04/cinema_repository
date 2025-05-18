package com.umarov.userservice.mapper;

import com.umarov.userservice.dto.UserResponse;
import com.umarov.userservice.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserResponse userToUserResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .username(user.getUsername())
                .build();
    }
}
