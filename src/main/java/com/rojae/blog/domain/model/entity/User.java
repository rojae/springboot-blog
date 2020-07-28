package com.rojae.blog.domain.model.entity;

import javax.persistence.*;

import com.rojae.blog.application.utility.ClientUtils;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends Time{
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    // mappedBy 추가
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Post> post = new ArrayList<Post>();

    @Column(name = "userId", nullable = false)
    String userId;

    @Column(name = "userName", nullable = false)
    String userName;

    @Column(name = "userEmail", nullable = false)
    String userEmail;

    @Column(name = "accessToken", nullable = false)
    String accessToken;

    @Column(name = "socialType", nullable = false)
    String socialType;

    @Column(name = "thumbNail", nullable = true)
    String thumbNail;

    @Column(name = "sIp", nullable =  false)
    private String sIp;

    @Builder
    public User(Long id, String userId, String userName, String userEmail, String accessToken, String socialType, String thumbNail, String sIp){
        this.id = id;
        this.userId = userId;
        this.userName = userName;
        this.userEmail = userEmail;
        this.accessToken = accessToken;
        this.socialType = socialType;
        this.thumbNail = thumbNail;
        this.sIp = sIp;
    }

}
