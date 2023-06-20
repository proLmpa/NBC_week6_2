package com.sparta.post.dto;

import lombok.Getter;

@Getter
public class StatusResponseDto {
    private Long status;
    private String message;

    public StatusResponseDto(Long status, String message) {
        this.status = status;
        this.message = message;
    }
}
