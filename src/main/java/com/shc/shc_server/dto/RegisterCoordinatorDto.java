package com.shc.shc_server.dto;

import lombok.Data;

@Data
public class RegisterCoordinatorDto {
    private String name;
    private String email;
    private String password;
    private String position;
}