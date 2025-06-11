package com.example.newsfeedproject.domain.user.entity;

import com.example.newsfeedproject.domain.common.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name="user")
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    public User(){}
    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
