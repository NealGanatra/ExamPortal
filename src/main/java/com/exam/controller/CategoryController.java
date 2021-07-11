package com.exam.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.entities.exam.Category;
import com.exam.services.CategoryService;

@RestController
@RequestMapping("/category")
@CrossOrigin("*")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@PostMapping("/")
	public ResponseEntity<?> addCategory(@RequestBody Category category){
		Category category1=this.categoryService.addCategory(category);
		return ResponseEntity.ok(category1);
	}

	@GetMapping("/{categoryId}")
	public Category getCategory(@PathVariable Long categoryId){
		return this.categoryService.getCategory(categoryId);
	}
	
	@GetMapping("/")
	public ResponseEntity<?> getCategories(){
		Set<Category> set=this.categoryService.getCategories();
		return ResponseEntity.ok(set);
	}
	
	@PutMapping("/")
	public ResponseEntity<?> updateCategory(@RequestBody Category category){
		Category category1=this.categoryService.updateCategory(category);
		return ResponseEntity.ok(category1);
	}
	
	@DeleteMapping("/{categoryId}")
	public void deleteCategory(@PathVariable Long categoryId) {
		this.categoryService.deleteCategory(categoryId);
	}
}
