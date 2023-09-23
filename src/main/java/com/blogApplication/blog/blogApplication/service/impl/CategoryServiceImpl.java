package com.blogApplication.blog.blogApplication.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogApplication.blog.blogApplication.entities.Category;
import com.blogApplication.blog.blogApplication.exception.ResourceNotFoundException;
import com.blogApplication.blog.blogApplication.payloads.CategoryDto;
import com.blogApplication.blog.blogApplication.repository.CategoryRepo;
import com.blogApplication.blog.blogApplication.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		Category category=this.modelMapper.map(categoryDto, Category.class);
		return this.modelMapper.map(this.categoryRepo.save(category), CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
		Category cat=this.categoryRepo.findById(categoryId)
				.orElseThrow(()->new ResourceNotFoundException("Category","CategoryId",categoryId));
		
		cat.setCategoryDescription(categoryDto.getCategoryDescription());
		cat.setCategoryTitle(categoryDto.getCategoryTitle());
		
		Category updatedCategory=this.categoryRepo.save(cat);
		
		
		return this.modelMapper.map(updatedCategory, CategoryDto.class);
	}

	@Override
	public void deleteCategory(Integer categoryId) {
		Category cat=this.categoryRepo.findById(categoryId)
				.orElseThrow(()->new ResourceNotFoundException("Category","CategoryId",categoryId));
		
		this.categoryRepo.delete(cat);
	}

	@Override
	public CategoryDto getCategory(Integer categoryId) {
		Category cat=this.categoryRepo.findById(categoryId)
				.orElseThrow(()->new ResourceNotFoundException("Category","CategoryId",categoryId));
		return this.modelMapper.map(cat, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getCategories() {
		List<Category> categories=this.categoryRepo.findAll();
		List<CategoryDto> catDtos=categories.stream()
				.map((category)->this.modelMapper.map(category,CategoryDto.class)).collect(Collectors.toList());
		return catDtos;
	}

}
