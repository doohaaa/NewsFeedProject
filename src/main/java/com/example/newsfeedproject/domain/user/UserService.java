package com.example.newsfeedproject.domain.user;

import com.example.newsfeedproject.config.PasswordEncoder;
import com.example.newsfeedproject.domain.user.dto.CreateProfileResponseDto;
import com.example.newsfeedproject.domain.user.dto.LoginResponseDto;
import com.example.newsfeedproject.domain.user.dto.ProfileResponseDto;
import com.example.newsfeedproject.domain.user.dto.SignUpUserResponseDto;
import com.example.newsfeedproject.domain.user.entity.User;
import com.example.newsfeedproject.domain.user.entity.UserProfile;
import lombok.RequiredArgsConstructor;
import org.hibernate.boot.model.naming.IllegalIdentifierException;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ProfileRepository profileRepository;

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

    public CreateProfileResponseDto createProfile(Long userId, String name, String info){
        UserProfile profile = new UserProfile(name, info, userId);
        UserProfile savedprofile = profileRepository.save(profile);

        return new CreateProfileResponseDto(savedprofile.getId(), savedprofile.getName(), savedprofile.getInfo());

    }

    public ProfileResponseDto findByUserId(Long id){
        Optional<UserProfile> optionalProfile = profileRepository.findByUserId(id);

        // NPE 받지
        if(optionalProfile.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exit id = "+id);
        }

        UserProfile findProfile = optionalProfile.get();

        return new ProfileResponseDto(findProfile.getName(), findProfile.getInfo());


    }
}
