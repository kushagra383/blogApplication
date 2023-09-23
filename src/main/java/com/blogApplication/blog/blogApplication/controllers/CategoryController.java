package com.blogApplication.blog.blogApplication.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blogApplication.blog.blogApplication.payloads.ApiResponse;
import com.blogApplication.blog.blogApplication.payloads.CategoryDto;
import com.blogApplication.blog.blogApplication.payloads.UserDto;
import com.blogApplication.blog.blogApplication.service.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto) {
		CategoryDto createdCategory = this.categoryService.createCategory(categoryDto);

		return new ResponseEntity<CategoryDto>(createdCategory, HttpStatus.CREATED);
	}

	@PutMapping("/{catId}")
	public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto,
			@PathVariable Integer catId) {
		CategoryDto updatedCategory = this.categoryService.updateCategory(categoryDto, catId);

		return new ResponseEntity<CategoryDto>(updatedCategory, HttpStatus.OK);

	}
	
	@DeleteMapping("/{catId}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer catId){
		this.categoryService.deleteCategory(catId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Category deleted Successfully",true),
				HttpStatus.OK);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getAllCategories(){
		return ResponseEntity.ok(this.categoryService.getCategories());
	}
	
	@GetMapping("/{catId}")
	public ResponseEntity<CategoryDto> getSingleCategory(@PathVariable Integer catId){
		return ResponseEntity.ok(this.categoryService.getCategory(catId));
	}
}
