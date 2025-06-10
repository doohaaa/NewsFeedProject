package com.example.newsfeedproject.domain.user;

import com.example.newsfeedproject.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
