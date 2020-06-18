package com.rojae.blog.domain.model.entity;

import javax.persistence.*;

import com.sun.istack.NotNull;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.stereotype.Component;

import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends Time{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    String userId;

    @Column(nullable = false)
    String userName;

    @Column(nullable = false)
    String userEmail;

    @Column(nullable = false)
    String accessToken;

    @Builder
    public User(Long id, String userId, String userName, String userEmail, String accessToken){
        this.id = id;
        this.userId = userId;
        this.userName = userName;
        this.userEmail = userEmail;
        this.accessToken = accessToken;
    }
}
