package com.app.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.common.ApiResponse;
import com.app.dto.ProductDTO;
import com.app.entity.Category;
import com.app.repository.CategoryRepo;
import com.app.service.CategoryService;
import com.app.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	ProductService productService;
	
	@Autowired
	CategoryRepo categoryRepo;
	
	@Autowired
	CategoryService categoryService;
	
	@GetMapping("/getProducts")
	public ResponseEntity<List<ProductDTO>> getAll()
	{
		return new ResponseEntity<>(productService.getAll(),HttpStatus.OK);
	}
	{
		
	}
	
	@PostMapping
	public ResponseEntity<ApiResponse> createProduct(ProductDTO productDto)
	{
		Optional<Category> optionalCategory=categoryRepo.findById(productDto.getCategoryId());
		
		if(!optionalCategory.isPresent())
		{
			return new ResponseEntity<ApiResponse>(new ApiResponse(false, "Category is not present"),HttpStatus.NOT_FOUND);
		}
		productService.createProduct(productDto,optionalCategory.get());
		return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Product has been Added"),HttpStatus.CREATED);
	}
	
	 @PostMapping("/update/{productID}")
	    public ResponseEntity<ApiResponse> updateProduct(@PathVariable("productID") Integer productID, @RequestBody @Valid ProductDTO productDto) {
	        Optional<Category> optionalCategory = categoryService.readCategory(productDto.getCategoryId());
	        if (!optionalCategory.isPresent()) {
	            return new ResponseEntity<ApiResponse>(new ApiResponse(false, "category is invalid"), HttpStatus.CONFLICT);
	        }
	        Category category = optionalCategory.get();
	        productService.updateProduct(productID, productDto, category);
	        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Product has been updated"), HttpStatus.OK);
	    }
}
