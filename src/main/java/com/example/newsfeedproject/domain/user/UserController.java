package com.example.newsfeedproject.domain.user;

import com.example.newsfeedproject.domain.user.dto.SignUpUserRequestDto;
import com.example.newsfeedproject.domain.user.dto.SignUpUserResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<SignUpUserResponseDto> signup (@RequestBody @Valid SignUpUserRequestDto requestDto){
        SignUpUserResponseDto signUpUserResponseDto =
                userService.signup(
                        requestDto.getEmail(), requestDto.getPassword()
                );
        return new ResponseEntity<>(signUpUserResponseDto, HttpStatus.CREATED);
    }


}
