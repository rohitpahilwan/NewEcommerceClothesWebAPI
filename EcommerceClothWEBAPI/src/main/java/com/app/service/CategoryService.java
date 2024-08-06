package com.app.service;
import java.util.List;
import com.app.entity.Category;

public interface CategoryService {
  
	public List<Category> getAll();
	
	public void createCategory(Category category);
	
	public String updateCategory(Long id,Category category);
	
	public String deleteById(Long id);
}
