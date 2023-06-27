package com.sparta.bulletin.user.dto;

import lombok.Getter;

@Getter
public class UserResponseDto {
    private Long status;
    private String message;

    public UserResponseDto(Long status, String message) {
        this.status = status;
        this.message = message;
    }
}
