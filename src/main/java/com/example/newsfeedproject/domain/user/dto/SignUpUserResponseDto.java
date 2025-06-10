package com.example.newsfeedproject.domain.user.dto;

import lombok.Getter;

@Getter
public class SignUpUserResponseDto {
    private final Long id;
    private final String email;

    public SignUpUserResponseDto(Long id, String email){
        this.id = id;
        this.email = email;
    }
}
