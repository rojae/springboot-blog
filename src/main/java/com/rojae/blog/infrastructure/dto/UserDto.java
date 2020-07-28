package com.rojae.blog.infrastructure.dto;

import java.time.LocalDateTime;
import java.util.Date;


import com.rojae.blog.domain.model.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.context.annotation.Configuration;

@Getter	// 모든 필드의 getter 메소드를 자동으로 생성해주는 어노테이션.
@Setter	// setter 메소드 ''
@ToString	// toString 메소드 ''
@NoArgsConstructor	// 매개변수가 없는 기본 생성자 ''
@Configuration
public class UserDto {

    private Long id;
    private String userId;
    private String userName;
    private String userEmail;
    private String accessToken;
    private String socialType;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private String thumbNail;
    private String sIp;

    public User toEntity(){
        User build = User.builder()
                .id(id)
                .userId(userId)
                .userName(userName)
                .userEmail(userEmail)
                .accessToken(accessToken)
                .socialType(socialType)
                .thumbNail(thumbNail)
                .sIp(sIp)
                .build();

        return build;
    }

    @Builder
    public UserDto(Long id, String userId, String userName, String userEmail, String accessToken, String socialType,
                   LocalDateTime createdDate, LocalDateTime modifiedDate, String thumbNail, String sIp) {
        this.id = id;
        this.userId = userId;
        this.userName = userName;
        this.userEmail = userEmail;
        this.accessToken = accessToken;
        this.socialType = socialType;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.thumbNail = thumbNail;
        this.sIp = sIp;
    }



}
