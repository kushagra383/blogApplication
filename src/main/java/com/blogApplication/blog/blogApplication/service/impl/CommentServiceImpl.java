package com.blogApplication.blog.blogApplication.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogApplication.blog.blogApplication.entities.Comment;
import com.blogApplication.blog.blogApplication.entities.Post;
import com.blogApplication.blog.blogApplication.exception.ResourceNotFoundException;
import com.blogApplication.blog.blogApplication.payloads.CommentDto;
import com.blogApplication.blog.blogApplication.repository.CommentRepo;
import com.blogApplication.blog.blogApplication.repository.PostRepo;
import com.blogApplication.blog.blogApplication.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {
	
	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private CommentRepo commentRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postId) {
		Post post=this.postRepo.findById(postId).
				orElseThrow(()->new ResourceNotFoundException("Post", "PostId", postId));
		
		Comment comment=this.modelMapper.map(commentDto, Comment.class);
		comment.setPost(post);
		Comment updatedComment=this.commentRepo.save(comment);
		return this.modelMapper.map(updatedComment, CommentDto.class);
	}

	@Override
	public void deleteComment(Integer commentId) {
		Comment comment=this.commentRepo.findById(commentId).
				orElseThrow(()->new ResourceNotFoundException("Comment", "CommentId", commentId));
		this.commentRepo.deleteById(commentId);

	}

}
