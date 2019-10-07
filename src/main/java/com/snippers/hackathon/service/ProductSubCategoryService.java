/**
 * 
 */
package com.snippers.hackathon.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.snippers.hackathon.entity.ProductSubCategory;
import com.snippers.hackathon.exception.ProductCategoryNotFoundException;
import com.snippers.hackathon.exception.ProductSubCategoryNotFoundException;
import com.snippers.hackathon.model.ProductSubCategoryDTO;
import com.snippers.hackathon.repository.ProductCategoryRepository;
import com.snippers.hackathon.repository.ProductSubCategoryRepository;

/**
 * @author rmamidala
 *
 */
@Service
public class ProductSubCategoryService {
	
	
	@Autowired
	private ProductCategoryRepository prodCategoryRepository;
	
	@Autowired
	private ProductSubCategoryRepository prodSubCategoryRepository;
	
	public List<ProductSubCategoryDTO> getAllProductSubCategoriesByCategoryId(Long productCategoryId) {
		return prodSubCategoryRepository.findByProductCategoryId(productCategoryId).stream().map(psc -> {
			ProductSubCategoryDTO productSubCategoryDTO = new ProductSubCategoryDTO();
			productSubCategoryDTO.setSubCategoryId(psc.getId());
			productSubCategoryDTO.setSubCategoryName(psc.getSubCategoryName());
			productSubCategoryDTO.setSubCategoryDescr(psc.getSubCategoryDescr());
			return productSubCategoryDTO;
		}).collect(Collectors.toList());
	}
	
	public void createProductSubCategory(Long productCategoryId, ProductSubCategoryDTO productSubCategoryDTO) {
			prodCategoryRepository.findById(productCategoryId).map(pc -> {
			ProductSubCategory productSubCategory = new ProductSubCategory();
			productSubCategory.setProductCategory(pc);
			productSubCategory.setSubCategoryName(productSubCategoryDTO.getSubCategoryName());
			productSubCategory.setSubCategoryDescr(productSubCategoryDTO.getSubCategoryDescr());
			return prodSubCategoryRepository.save(productSubCategory);
		}).orElseThrow(() -> new ProductCategoryNotFoundException("ProductCategory is not found with this id::"+productCategoryId+" for creating the ProductSubCategory::"));
	}
	
	public void updateProductSubCategory(Long productCategoryId,Long id, ProductSubCategoryDTO productSubCategoryDTO) {
		
		if(!prodCategoryRepository.existsById(productCategoryId)) {
			throw new ProductCategoryNotFoundException("ProductCategory is not found with this id::"+productCategoryId);
		}
		
		ProductSubCategory productSubCategory = prodSubCategoryRepository.findById(id)
				.orElseThrow(() -> new ProductSubCategoryNotFoundException("ProductSubCategory is not found with this id::"+id));
		productSubCategory.setSubCategoryName(productSubCategoryDTO.getSubCategoryName());
		productSubCategory.setSubCategoryDescr(productSubCategoryDTO.getSubCategoryDescr());
		prodSubCategoryRepository.save(productSubCategory);
	}
	
	public void deleteProductSubCategoryById(Long productCategoryId, Long id) {
		ProductSubCategory productSubCategory = prodSubCategoryRepository.findByIdAndProductCategoryId(id, productCategoryId)
		.orElseThrow(() -> new ProductSubCategoryNotFoundException("ProductSubCategory is not found with this id::"+id));
		prodSubCategoryRepository.delete(productSubCategory);
	}

}
