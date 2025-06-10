package com.example.newsfeedproject.domain.user.dto;

import lombok.Getter;

@Getter
public class SignUpUserRequestDto {
    private final String email;
    private final String password;

    public SignUpUserRequestDto(String email, String password){
        this.email = email;
        this.password = password;
    }

}
