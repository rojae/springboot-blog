package com.rojae.blog.infrastructure.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.rojae.blog.domain.model.entity.Post;

public interface PostDao extends JpaRepository<Post, Integer> {
}