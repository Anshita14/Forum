package com.social.forum.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.social.forum.entity.ForumPostComment;

@Repository
public interface CommentRepo extends JpaRepository<ForumPostComment, Long>{

}
