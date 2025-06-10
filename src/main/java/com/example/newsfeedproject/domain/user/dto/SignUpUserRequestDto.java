package com.example.newsfeedproject.domain.user.dto;

import lombok.Getter;
import jakarta.validation.constraints.*;

@Getter
public class SignUpUserRequestDto {

    @NotBlank(message = "이메일은 필수 입력 항목입니다.")
    @Email(message = "올바른 이메일 형식이 아닙니다.")
    private final String email;
    private final String password;

    public SignUpUserRequestDto(String email, String password){
        this.email = email;
        this.password = password;
    }

}
