package com.example.newsfeedproject.domain.user.dto;

import lombok.Getter;

@Getter
public class CreateProfileRequestDto {

    private final String name;
    private final String info;

    public CreateProfileRequestDto(String name, String info){
        this.name = name;
        this.info = info;
    }
}
