package com.rojae.blog.infrastructure.dao;

import com.rojae.blog.domain.model.entity.Member;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "user")
public interface UserRepository extends PagingAndSortingRepository<Member, Long> {

}
