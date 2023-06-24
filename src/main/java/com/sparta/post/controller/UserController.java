package com.sparta.post.controller;

import com.sparta.post.dto.UserRequestDto;
import com.sparta.post.dto.UserResponseDto;
import com.sparta.post.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController // API 형식으로 반환하는 Controller 형식
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user/signup")
    public UserResponseDto signup(@Valid @RequestBody UserRequestDto requestDto, BindingResult bindingResult) {
        // Validation 예외 처리
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        if(fieldErrors.size() > 0) {
            for(FieldError fieldError : bindingResult.getFieldErrors()) {
                log.error(fieldError.getField() + " 필드 : " + fieldError.getDefaultMessage());
            }
            return new UserResponseDto(401L, "SIGN_UP_FAILURE");
        }

        userService.signup(requestDto);
        return new UserResponseDto(200L, "SIGN_UP_SUCCESS");
    }
}