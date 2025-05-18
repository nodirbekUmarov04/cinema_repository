package com.umarov.userservice.dto;

import lombok.Data;

@Data
public class UserAddRequest {
    private String email;
    private String username;
    private String password;
}
