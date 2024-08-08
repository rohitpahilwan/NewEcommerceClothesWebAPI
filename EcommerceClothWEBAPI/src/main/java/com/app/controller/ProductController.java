package com.app.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

	@PutMapping("/update/{productID}")
	public ResponseEntity<ApiResponse> updateProduct(@PathVariable("productID") Long Id, @RequestBody  ProductDTO productDto) throws Exception {
		Optional<Category> optionalCategory=categoryRepo.findById(productDto.getCategoryId());
		if(!optionalCategory.isPresent())
		{
			return new ResponseEntity<ApiResponse>(new ApiResponse(false, "Category Not present"),HttpStatus.NOT_FOUND);
		}
		productService.updateProduct(Id, productDto,optionalCategory.get());
		return new ResponseEntity<ApiResponse>(new ApiResponse(true, "UPDATED SUCCESSFULLY"),HttpStatus.OK);
	}

	@DeleteMapping("/delete/{productID}")
	public ResponseEntity<ApiResponse> deleteProduct(@PathVariable("productID") Long Id)
	{
		if(!productService.findById(Id))
		{
			return new ResponseEntity<ApiResponse>(new ApiResponse(false, "Product Not present"),HttpStatus.NOT_FOUND);
		}
		productService.deleteProduct(Id);
		return new ResponseEntity<ApiResponse>(new ApiResponse(true, "DELETED SUCCESSFULLY"),HttpStatus.OK);
	}

}
		 
