package com.sparta.post.controller;

import com.sparta.post.dto.LoginRequestDto;
import com.sparta.post.dto.SignupRequestDto;
import com.sparta.post.dto.StatusResponseDto;
import com.sparta.post.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // API 형식으로 반환하는 Controller 형식
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user/signup")
    public StatusResponseDto signup(@Valid @RequestBody SignupRequestDto requestDto) {
        userService.signup(requestDto);
        return new StatusResponseDto(200L, "SIGN_UP_SUCCESS");
    }

    @PostMapping("/user/login")
    public StatusResponseDto login(@Valid @RequestBody LoginRequestDto requestDto, HttpServletResponse res) {
        try {
            userService.login(requestDto, res);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new StatusResponseDto(200L, "LOG_IN_SUCCESS");
    }
}