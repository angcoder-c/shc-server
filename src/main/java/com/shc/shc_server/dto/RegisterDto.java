package com.shc.shc_server.dto;

import lombok.Data;

@Data
public class RegisterDto {
    private String name;
    private String email;
    private String password;
    private String major;
    private Integer year;
}