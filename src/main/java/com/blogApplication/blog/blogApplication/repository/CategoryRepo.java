package com.blogApplication.blog.blogApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blogApplication.blog.blogApplication.entities.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer> {

}
