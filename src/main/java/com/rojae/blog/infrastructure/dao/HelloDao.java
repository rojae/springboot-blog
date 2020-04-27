package com.rojae.blog.infrastructure.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rojae.blog.domain.model.entity.Hello;

public interface HelloDao extends JpaRepository <Hello, Integer> {
}