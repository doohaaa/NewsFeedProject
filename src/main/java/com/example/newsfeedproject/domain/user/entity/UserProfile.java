package com.example.newsfeedproject.domain.user.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name="profile")
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column
    private String info;

    @Column
    private Long userId;

    public UserProfile(){}

    public UserProfile(String name, String info, Long userId){
        this.name = name;
        this.info = info;
        this.userId = userId;
    }

    public UserProfile(String name, String info){
        this.name = name;
        this.info = info;
    }


}
