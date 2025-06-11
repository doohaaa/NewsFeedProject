package com.example.newsfeedproject.domain.user;

import com.example.newsfeedproject.domain.user.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<UserProfile, Long> {
}
