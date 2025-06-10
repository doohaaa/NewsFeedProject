package com.example.newsfeedproject.domain.user;

import com.example.newsfeedproject.domain.user.dto.SignUpUserResponseDto;
import com.example.newsfeedproject.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public SignUpUserResponseDto signup(String email, String password){
        User user = new User(email, password);
        User signupUser = userRepository.save(user);
        return new SignUpUserResponseDto(signupUser.getId(),signupUser.getEmail());

    }
}
