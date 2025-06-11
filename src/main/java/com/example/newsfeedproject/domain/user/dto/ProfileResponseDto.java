package com.example.newsfeedproject.domain.user.dto;

import lombok.Getter;

@Getter
public class ProfileResponseDto {

    private final String userName;
    private final String userInfo;

    public ProfileResponseDto(String userName, String userInfo){
        this.userName = userName;
        this.userInfo = userInfo;
    }
}
