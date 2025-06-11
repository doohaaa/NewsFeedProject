package com.example.newsfeedproject.domain.user.dto;

import lombok.Getter;

@Getter
public class CreateProfileResponseDto {

    private final Long userId;
    private final String name;
    private final String info;

    public CreateProfileResponseDto(Long userId, String name, String info) {
        this.userId = userId;
        this.name = name;
        this.info = info;
    }
}
