package io.firesoft.controller;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.firesoft.model.Post;
import io.firesoft.model.RegistrationType;
import io.firesoft.model.User;
import io.firesoft.service.PostService;
import io.firesoft.service.PostServiceImpl;
import io.firesoft.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userservice;
	
	@Autowired
	private PostService postService;
	
	@ModelAttribute("user")
	public User constructUser() {
		return new User();
	}
	
	@ModelAttribute("post")
	public Post constructPost() {
		return new Post();
	}
	@RequestMapping("/users")
	public String users(Model model){
		model.addAttribute("users", userservice.findAll());
		return "users";
	}
	
	@RequestMapping("/users/{id}")
	public String detail(Model model, @PathVariable int id){
		model.addAttribute("user",userservice.findOneWithPosts(id));
		return "user-detail";
	}
	

	 @RequestMapping(value="/index", method=RequestMethod.POST)
	   public String doRegister(@ModelAttribute("user") User user){
		   user.setRegType(RegistrationType.Local);
		   userservice.save(user);
		   return "redirect:/index.html?success=true";
	 }	
	 
	 @RequestMapping("/account")
	 public String account(Model model, Principal principal) {
		 String name = principal.getName();
		 model.addAttribute("user", userservice.findOneWithPosts(name));
		 return "user-detail";
	 }
	 
	 @RequestMapping(value="/account", method=RequestMethod.POST)
	   public String doAddPost(@ModelAttribute("post") Post post, Principal principal){
		 String name = principal.getName();
		 postService.save(post, name);		 
		   return "redirect:/account.html";
	 }	
	 
	@RequestMapping("/users/remove/{id}")
	public String removeUser(@PathVariable int id){
		userservice.delete(id);
		return "redirect:/users.html";
	}
	
	
	
}
