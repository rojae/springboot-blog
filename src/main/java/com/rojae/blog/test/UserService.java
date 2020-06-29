package com.rojae.blog.test;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import javax.transaction.Transactional;

import com.rojae.blog.application.common.error.UnsameTokenException;
import com.rojae.blog.domain.model.entity.User;
import com.rojae.blog.infrastructure.dao.UserDao;
import com.rojae.blog.infrastructure.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service("userService")
public class UserService {

	@Autowired
	private UserDao userDao;

	// basic saver
	@Transactional
	public User saveUser(UserDto userDto){
		return userDao.save(userDto.toEntity());	// JpaRepository에 정의된 메서드로 DB에 insert, update를 담당, 매개변수로는 Entity를 전달.
	}

	// facebook saver
	public User facebookUser(UserDto userDto){
		return userDao.save(userDto.toEntity());
	}

	// google saver
	public User GoogleUser(UserDto userDto){
		return userDao.save(userDto.toEntity());
	}

	// kakao saver
	public User KakaoUser(UserDto userDto){
		return userDao.save(userDto.toEntity());
	}

	// naver saver
	public User NaverUser(UserDto userDto){
		return userDao.save(userDto.toEntity());
	}

	@Transactional
	public UserDto getInfo(String accessToken){
		// jdk 7 Optional -> Collection & ArrayList
		Collection<User> authEntityWrapper = userDao.findByAccessToken(accessToken);
		if(authEntityWrapper.size() < 1)
			throw new UnsameTokenException();

		ArrayList<User> list = new ArrayList<User>(authEntityWrapper);
		User user = list.get(0);

		UserDto userDto = UserDto.builder()
				.id(user.getId())
				.userName(user.getUserId())
				.userEmail(user.getUserEmail())
				.accessToken(user.getAccessToken())
				.createdDate(user.getCreatedDate())
				.modifiedDate(user.getModifiedDate())
				//.refreshToken(authEntity.getRefreshToken())
				.build();

		return userDto;
	}

	@Transactional
	public boolean checkToken(String accessToken){
		if(userDao.findByAccessToken(accessToken).size() >= 1)
			return true;
		else
			throw new UnsameTokenException();
	}
}
