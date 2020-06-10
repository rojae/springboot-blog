package com.rojae.blog.domain.model.entity;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member extends Time implements Serializable{
    @Id
    @GeneratedValue
    @NotNull
    Long id;

    @NotNull
    String userEmail;

    @NotNull
    String username;

    @NotNull
    String accessToken;

    @NotNull
    String Expire_Time;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    public String getRoleKey() {
        return this.role.getKey();
    }
}
