package com.example.newsfeedproject.domain.user;

import com.example.newsfeedproject.config.PasswordEncoder;
import com.example.newsfeedproject.domain.user.dto.LoginResponseDto;
import com.example.newsfeedproject.domain.user.dto.SignUpUserResponseDto;
import com.example.newsfeedproject.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.hibernate.boot.model.naming.IllegalIdentifierException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public SignUpUserResponseDto signup(String email, String password){
        // User user = new User(email, password);

        // 아이디 중복 확인
        if(userRepository.existsByEmail(email)){
            throw new IllegalArgumentException("이미 사용중인 아이디입니다.");
        }
        User encryptedUser = new User(email,passwordEncoder.encode(password));
        User signupUser = userRepository.save(encryptedUser);
        return new SignUpUserResponseDto(signupUser.getId(),signupUser.getEmail());

    }


    public LoginResponseDto login(String email, String password){
        final User user = userRepository.findByEmail(email)
                .orElseThrow(()->new IllegalIdentifierException("User not found. email = "+email));

        if(!passwordEncoder.matches(password, user.getPassword())){
            throw new IllegalArgumentException("Password is incorrect. email ="+ email);
        }

        // 변경해야함
        return LoginResponseDto.of(user);
    }

}
