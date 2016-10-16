package io.firesoft.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import io.firesoft.model.Post;
import io.firesoft.model.User;

public interface PostRepository  extends JpaRepository<Post, Integer>{
	List<Post> findByUser(User user, Pageable pageable);
}

	
	