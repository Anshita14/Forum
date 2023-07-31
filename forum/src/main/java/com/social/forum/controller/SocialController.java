package com.social.forum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.social.forum.entity.ForumPostTweet;
import com.social.forum.entity.User;
import com.social.forum.service.PostService;

@Controller
@RequestMapping(path = "/social")

public class SocialController  {
	
	@Autowired
	private PostService postService;
	
	@GetMapping(path="/login")
    public String goToLogin(){

        return "/Authentication/login";

    }
	
	@GetMapping(path="/forum")
	public String goToTweet() {
		
		return "/Forum/forum";
	}
	
	@PostMapping(path="/post")
	public String postTweet(@ModelAttribute ForumPostTweet post){
		postService.saveTweet(post);
		
		return "redirect:/social/forum";
	}
	
	@PostMapping(path="/createUser")
	public String createUser(@ModelAttribute User user) {
		postService.saveUser(user);
		
		return "redirect:/social/login";
	}

}

