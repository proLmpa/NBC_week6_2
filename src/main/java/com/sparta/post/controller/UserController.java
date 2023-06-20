package com.sparta.post.controller;

import com.sparta.post.dto.SignupRequestDto;
import com.sparta.post.dto.StatusResponseDto;
import com.sparta.post.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user/signup")
    public StatusResponseDto signup(SignupRequestDto requestDto) {
        userService.signup(requestDto);
        return new StatusResponseDto(200L, "SIGN_UP_SUCCESS");
    }
}