package com.app.service;

import java.util.List;
import java.util.Optional;

import com.app.dto.ProductDTO;
import com.app.entity.Category;

public interface ProductService {

	public List<ProductDTO> getAll();
	
	public void createProduct(ProductDTO productDTO,Category category);

	public void updateProduct(Long id,ProductDTO productDto) throws Exception;

	public boolean findById(Long id);
}
