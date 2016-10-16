package io.firesoft.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.firesoft.model.Post;
import io.firesoft.model.User;
import io.firesoft.repository.PostRepository;
import io.firesoft.repository.UserRepository;

@Service("postService")
public class PostServiceImpl implements PostService {
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired UserRepository userRepository;

	@Transactional
	public Post save(Post post) {
		
		return postRepository.save(post);
	}

	@Override
	public List<Post> findAllPosts() {
	return postRepository.findAll();
	     
	}

	@Override
	public void save(Post post, String name) {
		User user = userRepository.findByUsername(name);
		post.setUser(user);
		postRepository.save(post);
		
		
	}

}
