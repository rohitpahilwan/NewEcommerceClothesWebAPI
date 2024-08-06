package com.app.controller;

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

import com.app.common.ApiResponse;
import com.app.entity.Category;
import com.app.service.CategoryService;

@RestController
@RequestMapping("/category")
public class categoryController {

	@Autowired
	CategoryService categoryService;
	
	@GetMapping("/categories")
	public List<Category> getAll()
	{
		return categoryService.getAll();
	}
	
@PostMapping("/create")
	public ResponseEntity<ApiResponse> createCategory(@RequestBody Category category)
	{
			categoryService.createCategory(category);
 			return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Category has been Created"),HttpStatus.CREATED);  
	}

@PutMapping("/updatecat/{id}")
public ResponseEntity<ApiResponse>  updateCategory(@PathVariable Long id,@RequestBody Category category)
{
	if(!categoryService.findById(id)) {
		return new ResponseEntity<ApiResponse>(new ApiResponse(false,"Id Not Found"),HttpStatus.NOT_FOUND);
	}
	categoryService.updateCategory(id, category);
	return new ResponseEntity<ApiResponse>(new ApiResponse(true,"Category has been Updated"),HttpStatus.OK);
}
	

@DeleteMapping("/deleteCat/{id}")
public String delete(@PathVariable Long id)
{
	return categoryService.deleteById(id);
}
}
