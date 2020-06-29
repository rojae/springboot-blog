package com.rojae.blog.infrastructure.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.rojae.blog.domain.model.entity.Post;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

public interface PostDao extends JpaRepository<Post, Integer> {
    public Post findAllByUserId(@Param("accessToken") String accessToken);
}