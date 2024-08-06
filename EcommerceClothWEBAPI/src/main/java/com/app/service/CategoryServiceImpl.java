package com.app.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.entity.Category;
import com.app.repository.CategoryRepo;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	CategoryRepo categoryRepo; 
	
	@Override
	public List<Category> getAll() {
		return categoryRepo.findAll();
		
	}

	@Override
	public void createCategory(Category category) {
		
	categoryRepo.save(category);	
	}

	@Override
	public String updateCategory(Long id, Category category) {
		Category updateCategory=categoryRepo.getById(id);
		if(updateCategory!=null)
		{
		updateCategory.setCategoryName(category.getCategoryName());
		updateCategory.setDescription(category.getDescription());
		updateCategory.setImageUrl(category.getImageUrl());
		updateCategory.setQuantity(category.getQuantity());
			
			categoryRepo.save(category);
			return "UPDATED!!!";
		}
		return "NOT FOUND";
	}

	@Override
	public String deleteById(Long id) {
		
		categoryRepo.deleteById(id);
		return "deleted";
	}


}
