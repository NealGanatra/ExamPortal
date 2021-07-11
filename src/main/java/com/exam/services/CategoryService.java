package com.exam.services;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.exam.entities.exam.Category;
import com.exam.repo.CategoryRepository;


public interface CategoryService {

	public Category addCategory(Category category);
	
	public Category updateCategory(Category category);
	
	public Set<Category> getCategories();
	
	public Category getCategory(Long categoryId);
	
	public void deleteCategory(Long categoryId);
	
	
}
