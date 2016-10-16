package io.firesoft.controller;


import io.firesoft.model.Post;
import io.firesoft.model.Themes;
import io.firesoft.service.PostService;
import io.firesoft.service.ThemeService;



import java.security.Principal;
import java.util.List;

import javax.validation.Valid;
import javax.ws.rs.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;


@Controller
@SessionAttributes("post")
public class PostController {
	
	@Autowired
	private ThemeService themeService;
	@Autowired
	private PostService postService;
	
	@RequestMapping(value="addPost", method = RequestMethod.GET)
	public String addGoal(Model model) {
		Post post = new Post();
		post.setPostDate();
		model.addAttribute("post", post);
		return "addPost";
	}
	
	@RequestMapping(value="addPost", method = RequestMethod.POST)
	public String updatePost(@Valid @ModelAttribute("post") Post post, BindingResult result, Principal principal){
		
		System.out.println("result has errors: " + result.hasErrors());
		System.out.println("post title: "+post.getTitle());
		System.out.println("post content: "+post.getContent());
		System.out.println("Theme of post is: "+post.getThemes());
		
		
		
		if (result.hasErrors()){
			return "addPost";
		}
		
		else {
			 String name = principal.getName();
			 postService.save(post, name);		 
			//postService.save(post);
		}
		return "redirect:/";
	}
	
	
	 
	@RequestMapping(value = "/themes", method = RequestMethod.GET)
	public @ResponseBody List<Themes> findALLThemes() {
	
		
		return themeService.findAllThemes();
		
	}

	@RequestMapping(value = "index", method = RequestMethod.GET)
	public String getPosts (Model model){
		List<Post> posts = postService.findAllPosts();
		model.addAttribute("posts",posts);
		return "index";
	}

}
