package com.social.forum.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.social.forum.entity.ForumPostTweet;

@Repository
public interface TweetRepo extends JpaRepository<ForumPostTweet, Long> {

}
