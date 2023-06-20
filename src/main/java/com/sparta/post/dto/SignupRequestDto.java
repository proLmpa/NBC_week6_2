package com.sparta.post.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequestDto {
    private String username;
    private String password;
    // private boolean admin = false;
}