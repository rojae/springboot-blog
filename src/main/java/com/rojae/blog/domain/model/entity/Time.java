package com.rojae.blog.domain.model.entity;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;

@Getter
@MappedSuperclass	// 테이블로 매핑하지 않고, 자식 클래스(엔티티)에게 매핑정보를 상속하기 위한 어노테이션.
@EntityListeners(AuditingEntityListener.class)	// JPA에게 해당 엔티티는 auditing 기능을 사용한다는 것을 알리는 어노테이션.
// 데이터 조작 시 자동으로 날짜를 수정해주는 JPA의 Auditing 기능을 사용.
public abstract class Time {
    @CreatedDate	// Entity가 처음 저장될 때 생성일을 주입하는 어노테이션.
    @Column(updatable = false)
    private LocalDateTime createdDate;

    @LastModifiedDate	// Entity가 수정될 때 수정일자를 자동 주입하는 어노테이션.
    private LocalDateTime modifiedDate;

}
