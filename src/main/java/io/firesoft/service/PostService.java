package io.firesoft.service;

import java.util.List;

import io.firesoft.model.Post;

public interface PostService {

	Post save (Post post);

	List<Post> findAllPosts();
	
	void save(Post post, String name);
}
