package com.shc.shc_server.dto;

import lombok.Data;

@Data
public class LoginDto {
    private String email;
    private String password;
}