package com.social.forum.service;

import com.social.forum.entity.ForumPostTweet;
import com.social.forum.entity.User;

public interface PostService {

	void saveTweet(ForumPostTweet post);

	void saveUser(User user);

}
