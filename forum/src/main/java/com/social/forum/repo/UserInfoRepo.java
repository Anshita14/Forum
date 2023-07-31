package com.social.forum.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.social.forum.entity.User;
import com.social.forum.entity.UserInfo;

@Repository
public interface UserInfoRepo extends JpaRepository<UserInfo, Long>{

	UserInfo findByUser(User user);

}
