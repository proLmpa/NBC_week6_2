package com.sparta.post.service;

import com.sparta.post.jwt.JwtUtil;
import com.sparta.post.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserServiceBuilder {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private JwtUtil jwtUtil;

    public UserServiceBuilder setUserRepository(UserRepository userRepository){
        this.userRepository = userRepository;
        return this;
    }

    public UserServiceBuilder setPasswordEncoder(PasswordEncoder passwordEncoder){
        this.passwordEncoder = passwordEncoder;
        return this;
    }

    public UserServiceBuilder setJwtUtil(JwtUtil jwtUtil){
        this.jwtUtil = jwtUtil;
        return this;
    }

    public UserService createService() {
        return new UserService(userRepository, passwordEncoder, jwtUtil);
    }
}
