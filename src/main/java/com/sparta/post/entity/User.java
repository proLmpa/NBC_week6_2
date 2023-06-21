package com.sparta.post.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Pattern(regexp="^[a-z0-9]{4,10}$", message="영소문자와 숫자(0~9)로 이뤄진 4자 이상 10자 이하의 값으로 이뤄졌습니다.")
    @Column(name = "username", nullable = false)
    private String username;

    @Pattern(regexp="^[a-zA-Z0-9]{8,15}$", message="영대소문자와 숫자(0~9)로 이뤄진 8자 이상 15자 이하의 값으로 이뤄졌습니다.")
    @Column(name = "password", nullable = false)
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}