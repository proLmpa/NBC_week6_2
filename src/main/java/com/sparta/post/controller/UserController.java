package com.sparta.post.controller;

import com.sparta.post.dto.UserRequestDto;
import com.sparta.post.dto.UserResponseDto;
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
    public UserResponseDto signup(@Valid @RequestBody UserRequestDto requestDto) {
        userService.signup(requestDto);
        return new UserResponseDto(200L, "SIGN_UP_SUCCESS");
    }

    @PostMapping("/user/login")
    public UserResponseDto login(@Valid @RequestBody UserRequestDto requestDto, HttpServletResponse res) {
        try {
            userService.login(requestDto, res);
        } catch (Exception e) {
            e.printStackTrace();
            return new UserResponseDto(401L, "LOG_IN_FAILURE");
        }

        return new UserResponseDto(200L, "LOG_IN_SUCCESS");
    }
}