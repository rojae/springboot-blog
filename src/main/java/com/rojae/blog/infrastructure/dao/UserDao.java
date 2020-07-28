package com.rojae.blog.infrastructure.dao;

import com.rojae.blog.domain.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

public interface UserDao extends JpaRepository<User, Long> {
    public Collection<User> findByAccessToken(@Param("accessToken") String accessToken);

    public User findByUserIdAndSocialType(@Param("userId") String userId, @Param("socialType") String socialType);

    @Transactional
    @Modifying
    @Query("UPDATE User u set u.accessToken = :newAccessToken where u.userId = :userId AND u.socialType = :socialType")
    public void updateAccessToken(@Param("userId") String userId,
                                  @Param("socialType") String socialType,
                                  @Param("newAccessToken") String newAccessToken);
}
