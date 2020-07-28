package com.rojae.blog.infrastructure.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.rojae.blog.domain.model.entity.Post;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface PostDao extends JpaRepository<Post, Integer> {
    //public Post findAllByUserId(@Param("userId") String userId);

    @Query("SELECT 1 FROM Post p WHERE p.id = :id AND p.user.id = :userId")
    public int findOneByUserIdAndId(@Param("id") int id, @Param("userId") Long userId);
}