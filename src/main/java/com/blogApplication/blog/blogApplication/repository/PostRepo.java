package com.blogApplication.blog.blogApplication.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import com.blogApplication.blog.blogApplication.entities.Category;
import com.blogApplication.blog.blogApplication.entities.Post;
import com.blogApplication.blog.blogApplication.entities.User;

public interface PostRepo extends JpaRepository<Post, Integer> {
	
	List<Post> findByUser(User user);
	List<Post> findByCategory(Category category);
	
	List<Post> findByTitleContaining(String title);
}
