package com.rojae.blog.infrastructure.dao;

import com.rojae.blog.domain.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

public interface UserDao extends JpaRepository<User, Long> {
    public Collection<User> findByAccessToken(@Param("accessToken") String accessToken);
}
