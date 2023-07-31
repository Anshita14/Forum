package com.social.forum.serviceImpl;

import java.sql.Date;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.social.forum.entity.ForumPostTweet;
import com.social.forum.entity.User;
import com.social.forum.entity.UserInfo;
import com.social.forum.repo.TweetRepo;
import com.social.forum.repo.UserInfoRepo;
import com.social.forum.repo.UserRepo;
import com.social.forum.service.PostService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PostServiceImpl implements PostService{

	@Autowired
	private TweetRepo tweetRepo;
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private UserInfoRepo infoRepo;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	
	@Override
	public void saveTweet(ForumPostTweet post) {
		
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = userRepo.findByUsername(userName);
		UserInfo userInfo = infoRepo.findByUser(user);
		Date date=Date.valueOf(LocalDate.now());
	    post.setPostingDate(date);
	    post.setUserInfo(userInfo);
		tweetRepo.save(post);
		
	}


	@Override
	public void saveUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepo.save(user);
		
		
	}

}
