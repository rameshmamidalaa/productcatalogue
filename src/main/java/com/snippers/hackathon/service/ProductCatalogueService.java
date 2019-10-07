/**
 * 
 */
package com.snippers.hackathon.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.snippers.hackathon.entity.Product;
import com.snippers.hackathon.entity.ProductSubCategory;
import com.snippers.hackathon.exception.ProductSubCategoryNotFoundException;
import com.snippers.hackathon.model.ProductDTO;
import com.snippers.hackathon.repository.ProductCatalogueRepository;
import com.snippers.hackathon.repository.ProductSubCategoryRepository;

/**
 * @author rmamidala
 *
 */
@Service
public class ProductCatalogueService {
	
	@Autowired
	private ProductCatalogueRepository repository;
	
	@Autowired
	private ProductSubCategoryRepository prodSubCategoryRepository;
	
	public List<ProductDTO> getProductCatalogue(Long productCategory_Id, Long productSubCategory_Id) {
		return repository.findProductByCategories(productCategory_Id, productSubCategory_Id).stream().map(product -> {
			ProductDTO productDTO = new ProductDTO();
			productDTO.setProductCode(product.getProductCode());
			productDTO.setProductName(product.getProductName());
			productDTO.setProductDescr(product.getProductDescr());
			productDTO.setPrice(product.getPrice());
			productDTO.setImageUrl(product.getImageUrl());
			productDTO.setSizes(product.getSizes());
			return productDTO;
		}).collect(Collectors.toList());
	}
	
	public void createProductCatalogue(Long productCategory_Id, Long productSubCategory_Id, List<ProductDTO> productCatalogue) {
		Optional<ProductSubCategory> productSubCategory = prodSubCategoryRepository.findByIdAndProductCategoryId(productSubCategory_Id, productCategory_Id);
		if(productSubCategory != null) {
			List<Product> products = new ArrayList<>();
			for(ProductDTO productDTO: productCatalogue) {
				products.add(addProductToCatalogue(new Product(), productDTO, productSubCategory));
			}
			repository.saveAll(products);
		} else {
			throw new ProductSubCategoryNotFoundException("ProductSubCategory is not found with the associated ids::"+productSubCategory_Id+" and "+productSubCategory_Id);
		}
		
	}
	private Product addProductToCatalogue(Product product, ProductDTO productDTO, Optional<ProductSubCategory> productSubCategory) {
		product.setProductCode(productDTO.getProductCode());
		product.setProductName(productDTO.getProductName());
		product.setProductDescr(productDTO.getProductDescr());
		product.setImageUrl(productDTO.getImageUrl());
		product.setPrice(productDTO.getPrice());
		product.setSizes(productDTO.getSizes().stream().collect(Collectors.toList()));
		product.setProductCategory(productSubCategory.get().getProductCategory());
		product.setProductSubCategory(productSubCategory.get());
		return product;
		
	}
	
	public List<ProductDTO> searchProducts(ProductDTO productDTO) {
		List<ProductDTO> productCatalogue = null;
		if(productDTO.getProductCode() != null) {
			productCatalogue = repository.findProductBySearchTerm(productDTO.getProductCode()).stream().map(product -> {
				ProductDTO productD = getProductDetails(product, new ProductDTO());
				return productD;
			}).collect(Collectors.toList());
			return productCatalogue;
		} else if(productDTO.getProductName() != null) {
			productCatalogue = repository.findProductBySearchTerm(productDTO.getProductName()).stream().map(product -> {
				ProductDTO productD = getProductDetails(product, new ProductDTO());
				return productD;
			}).collect(Collectors.toList());
		} else if(productDTO.getProductDescr() != null) {
			productCatalogue = repository.findProductBySearchTerm(productDTO.getProductDescr()).stream().map(product -> {
				ProductDTO productD = getProductDetails(product, new ProductDTO());
				return productD;
			}).collect(Collectors.toList());
		}
		return productCatalogue;
		
	}
	
	private ProductDTO getProductDetails(Product product, ProductDTO productDTO) {
		productDTO.setProductCode(product.getProductCode());
		productDTO.setProductName(product.getProductName());
		productDTO.setProductDescr(product.getProductDescr());
		productDTO.setPrice(product.getPrice());
		productDTO.setImageUrl(product.getImageUrl());
		productDTO.setSizes(product.getSizes());
		return productDTO;
	}
}
