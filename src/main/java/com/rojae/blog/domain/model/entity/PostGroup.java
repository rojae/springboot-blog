package com.rojae.blog.domain.model.entity;


import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity
public class PostGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    int nPostGroup;

    String sPostGroup;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    LocalDateTime createdDate;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    LocalDateTime modifiedDate;

    @NotNull
    boolean isEnable;

    @NotNull
    String sIp;
}