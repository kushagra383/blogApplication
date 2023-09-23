package com.blogApplication.blog.blogApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blogApplication.blog.blogApplication.entities.Comment;

public interface CommentRepo extends JpaRepository<Comment, Integer> {

}
