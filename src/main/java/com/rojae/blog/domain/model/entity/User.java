package com.rojae.blog.domain.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.sun.istack.NotNull;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import lombok.Builder;
import lombok.Getter;

@Component
@Getter
@Entity
@NoArgsConstructor
public class User extends Time{
    @Id
    @GeneratedValue
    int id;

    @NotNull
    String userName;

    @NotNull
    String userEmail;

    @NotNull
    String accessToken;

    @Builder
    public User(int id, String userName, String userEmail, String accessToken){
        this.id = id;
        this.userName = userName;
        this.userEmail = userEmail;
        this.accessToken = accessToken;
    }
}
