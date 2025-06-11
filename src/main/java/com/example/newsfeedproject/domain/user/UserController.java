package com.example.newsfeedproject.domain.user;

import com.example.newsfeedproject.domain.user.dto.LoginRequestDto;
import com.example.newsfeedproject.domain.user.dto.LoginResponseDto;
import com.example.newsfeedproject.domain.user.dto.SignUpUserRequestDto;
import com.example.newsfeedproject.domain.user.dto.SignUpUserResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<SignUpUserResponseDto> signup (@RequestBody @Valid SignUpUserRequestDto requestDto){
        SignUpUserResponseDto signUpUserResponseDto =
                userService.signup(
                        requestDto.getEmail(), requestDto.getPassword()
                );
        return new ResponseEntity<>(signUpUserResponseDto, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginRequestDto loginRequestDto, HttpServletRequest servletRequest){
        // 사용자 인증
        final LoginResponseDto loginResponseDto = userService.login(loginRequestDto.getEmail(), loginRequestDto.getPassword());

        // 세션 생성 및 저장
        final HttpSession session = servletRequest.getSession(); // 세션 없으면 생성
        session.setAttribute("user", loginResponseDto); // 유저 정보를 세션에 저장

        // session id 반환
        String sessionId = session.getId();

        return ResponseEntity.ok(Map.of("message", "로그인 성공", "sessionId", sessionId, "user", loginResponseDto));
    }

    @PostMapping("/logout")
    public ResponseEntity<Object> logout(HttpServletRequest servletRequest){
        HttpSession session = servletRequest.getSession(false);

        if(session != null){
            session.invalidate();
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
