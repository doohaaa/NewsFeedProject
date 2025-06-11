package com.example.newsfeedproject.domain.user;

import com.example.newsfeedproject.domain.user.dto.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.core.env.Profiles;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // 회원가입
    @PostMapping("/signup")
    public ResponseEntity<SignUpUserResponseDto> signup (@RequestBody @Valid SignUpUserRequestDto requestDto){
        SignUpUserResponseDto signUpUserResponseDto =
                userService.signup(
                        requestDto.getEmail(), requestDto.getPassword()
                );
        return new ResponseEntity<>(signUpUserResponseDto, HttpStatus.CREATED);
    }

    // 로그인
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

    // 로그아웃
    @PostMapping("/logout")
    public ResponseEntity<Object> logout(HttpServletRequest servletRequest){
        HttpSession session = servletRequest.getSession(false);

        if(session != null){
            session.invalidate();
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 프로필 생성
    @PostMapping("/profile")
    public ResponseEntity<CreateProfileResponseDto> createProfile (
            @RequestBody @Valid CreateProfileRequestDto requestDto,
            @SessionAttribute(name="user")LoginResponseDto loginResponseDto
            ){
        CreateProfileResponseDto  createProfileResponseDto =
                userService.createProfile(
                        loginResponseDto.getId(), requestDto.getName(), requestDto.getInfo()
                );

        // log.info("{}",loginResponseDto);
        return new ResponseEntity<>(createProfileResponseDto, HttpStatus.CREATED);
    }

    // 프로필 조회 -> 오류 생김
    @GetMapping("/profile/{id}")
    public ResponseEntity<ProfileResponseDto> findByUserId(@PathVariable Long id){

        // userService 에서 userId로 프로필 조회
        ProfileResponseDto profileResponseDto = userService.findByUserId(id);
        return new ResponseEntity<>(profileResponseDto, HttpStatus.OK);
    }
}
