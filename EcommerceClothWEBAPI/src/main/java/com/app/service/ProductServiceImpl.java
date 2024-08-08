package com.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dto.ProductDTO;
import com.app.entity.Category;
import com.app.entity.Product;
import com.app.repository.ProductRepo;


@Service
@Transactional
public class ProductServiceImpl implements ProductService{

	@Autowired
	ProductRepo productRepo;
	
	@Override
	public void createProduct(ProductDTO productDTO, Category category) {
		Product product=new Product();
		product.setDescrption(productDTO.getDescrption());
		product.setImgURL(productDTO.getImgURL());
		product.setName(productDTO.getName());
		product.setCategory(category);
		product.setPrice(productDTO.getPrice());
		productRepo.save(product);
		
	}

	public ProductDTO getproductDto (Product product)
	{
		ProductDTO productDto=new ProductDTO();
		productDto.setDescrption(product.getDescrption());
		productDto.setImgURL(product.getImgURL());
		productDto.setName(product.getName());
		productDto.setCategoryId(product.getCategory().getId());
		productDto.setPrice(product.getPrice());
		productDto.setPid(product.getPid());
		return productDto;

	}
	
	@Override
	public List<ProductDTO> getAll() {
		List<Product> allProducts=productRepo.findAll();
		List<ProductDTO> DTOProducts=new ArrayList<ProductDTO>();

		for(Product p:allProducts)
		{
			DTOProducts.add(getproductDto(p));
		}
		
		return DTOProducts;
	}

	@Override   
	public void updateProduct(Long id, ProductDTO productDTO) throws Exception
	{
		
		if(productRepo.findById(id).isPresent())
		{
			throw new Exception("Product is not present");
		}
		
		Product product=new Product();
		product.setDescrption(productDTO.getDescrption());
		product.setImgURL(productDTO.getImgURL());
		product.setName(productDTO.getName());
		product.setPrice(productDTO.getPrice());

		productRepo.save(product);
	}

	@Override
	public void deleteProduct(Long id) {
		productRepo.deleteById(id);
	}

	@Override
	public boolean findById(Long id) {
		if(productRepo.findById(id).isPresent())
		{
			return true;
		}
		return false;
	}
	
	

}
