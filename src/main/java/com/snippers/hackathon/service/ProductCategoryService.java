/**
 * 
 */
package com.snippers.hackathon.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.snippers.hackathon.entity.ProductCategory;
import com.snippers.hackathon.exception.ProductCategoryNotFoundException;
import com.snippers.hackathon.model.ProductCategoryDTO;
import com.snippers.hackathon.repository.ProductCategoryRepository;

/**
 * @author rmamidala
 *
 */
@Service
public class ProductCategoryService {
	
	@Autowired
	private ProductCategoryRepository repository;
	
	
	public List<ProductCategoryDTO> getAllProductCategories() {
		return repository.findAll().stream().map(pc -> {
			ProductCategoryDTO productCategoryDTO = new ProductCategoryDTO(pc.getId(), pc.getCategoryName(), pc.getCategoryDescr());
			return productCategoryDTO;
		}).collect(Collectors.toList());
	}
	
	public ProductCategoryDTO getProductCategoryById(Long id) {
		return repository.findById(id).map(pc -> {
			ProductCategoryDTO productCategoryDTO = new ProductCategoryDTO(pc.getId(), pc.getCategoryName(), pc.getCategoryDescr());
			return productCategoryDTO;
		}).orElseThrow(() -> new ProductCategoryNotFoundException("ProductCategory is not found with this id::"+id));
	}
	
	public void createProductCategory(ProductCategoryDTO productCategoryDTO) {
		ProductCategory productCategory = new ProductCategory();
		productCategory.setCategoryName(productCategoryDTO.getCategoryName());
		productCategory.setCategoryDescr(productCategoryDTO.getCategoryDescr());
		repository.save(productCategory);
	}
	
	public void updateProductCategory(Long id, ProductCategoryDTO productCategoryDTO) {
		ProductCategory productCategory = repository.findById(id)
				.orElseThrow(() -> new ProductCategoryNotFoundException("ProductCategory is not found with this id::"+id));
		productCategory.setCategoryName(productCategoryDTO.getCategoryName());
		productCategory.setCategoryDescr(productCategoryDTO.getCategoryDescr());
		repository.save(productCategory);
		
	}
	public void deleteProductCategoryById(Long id) {
		ProductCategory productCategory = repository.findById(id)
				.orElseThrow(() -> new ProductCategoryNotFoundException("ProductCategory is not found with this id::"+id));
		
		repository.delete(productCategory);
	}

}
