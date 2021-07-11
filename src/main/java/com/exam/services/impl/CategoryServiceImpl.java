package com.exam.services.impl;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.entities.exam.Category;
import com.exam.repo.CategoryRepository;
import com.exam.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Override
	public Category addCategory(Category category) {
		return this.categoryRepository.save(category);
		
	}

	@Override
	public Category updateCategory(Category category) {
		return this.categoryRepository.save(category);
		
	}

	@Override
	public Set<Category> getCategories() {
		// TODO Auto-generated method stub
		 return new LinkedHashSet<>(this.categoryRepository.findAll());
		 
	}

	@Override
	public Category getCategory(Long categoryId) {
		// TODO Auto-generated method stub
		Optional<Category> optional= this.categoryRepository.findById(categoryId);
		return optional.get();
	}

	@Override
	public void deleteCategory(Long categoryId) {
		// TODO Auto-generated method stub
		
		this.categoryRepository.deleteById(categoryId);
		
	}

}
