package com.rojae.blog.infrastructure.dao;

import com.rojae.blog.domain.model.entity.Member;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface MemberDao extends PagingAndSortingRepository<Member, Long> {

}
