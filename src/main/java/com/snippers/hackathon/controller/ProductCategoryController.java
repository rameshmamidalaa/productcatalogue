/**
 * 
 */
package com.snippers.hackathon.controller;

import java.util.List;

import javax.validation.Valid;

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

import com.snippers.hackathon.model.ProductCategoryDTO;
import com.snippers.hackathon.service.ProductCategoryService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

/**
 * @author rmamidala
 *
 */
@RestController
@RequestMapping("/api/v1")
@Slf4j
@Api(tags = {"Operations pertaining to ProductCategory Details"})
public class ProductCategoryController {
	
	@Autowired
	private ProductCategoryService service;
	
	@ApiOperation(value = "Retrieve available ProductCategory Details")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully retrieved the list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 401, message = "The resource you were trying to reach is not found")
	})
	@GetMapping("/productCategories")
	public ResponseEntity<List<ProductCategoryDTO>> getAllProductCategories() {
		log.info("Fetching all the available Product Categories");
		List<ProductCategoryDTO> productCategoryDTOs = service.getAllProductCategories();
		if(productCategoryDTOs.isEmpty()) {
			log.error("No Product Categories found::");
			return new ResponseEntity<List<ProductCategoryDTO>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<ProductCategoryDTO>>(productCategoryDTOs, HttpStatus.OK);
	}
	@ApiOperation(value = "Retrieve the specific ProductCategory Details by passing the ProductCategory ID")
	@GetMapping("/productCategories/{id}")
	public ResponseEntity<ProductCategoryDTO> getProductCategoryById(
			@ApiParam(value = "ProductCategory Id from which ProductCategory object will retrive", required = true) @PathVariable("id") Long id) {
		log.info("Fetching the requested Product Category with id {}", id);
		ProductCategoryDTO productCategoryDTO = service.getProductCategoryById(id);
		return new ResponseEntity<ProductCategoryDTO>(productCategoryDTO, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Create new ProductCategory Details by passing the ProductCategory Object")
	@PostMapping("/productCategories")
	public ResponseEntity<String> createProductCategory(
			@ApiParam(value = "ProductCategory object save in the database table", required = true) @Valid @RequestBody ProductCategoryDTO productCategoryDTO) {
		log.info("Creating a ProductCategory with the following details {}", productCategoryDTO);
		service.createProductCategory(productCategoryDTO);
		return new ResponseEntity<String>("New ProductCategory Information successfully created", HttpStatus.CREATED);
		
	}
	@ApiOperation(value = "Update the ProductCategory Details by passing the ProductCategory ID")
	@PutMapping("/productCategories/{id}")
	public ResponseEntity<String> updateProductCategory(
			@ApiParam(value = "ProductCategory Id to update the ProductCategory Object", required = true) @PathVariable Long id,
			@ApiParam(value = "Update the ProductCategory Object", required = true) @Valid @RequestBody ProductCategoryDTO productCategoryDTO) {
		log.info("Fetching & Updating ProductCategory with id {}", id);
		service.updateProductCategory(id, productCategoryDTO);
		return new ResponseEntity<String>("ProductCategory Info successfully updated for the requested id:::"+id, HttpStatus.OK );
	}
	
	@ApiOperation(value = "Delete the ProductCategory Details by passing the ProductCategory ID")
	@DeleteMapping("/productCategories/{id}")
	public ResponseEntity<String> deleteProductCategoryById(
			@ApiParam(value = "ProductCategory Id from which ProductCategory object will delete", required = true) @PathVariable Long id) {
		log.info("Deleting the requested Product Category with id {}", id);
		service.deleteProductCategoryById(id);
		return new ResponseEntity<String>("Product Category Information successfully deleted with id::"+id, HttpStatus.NO_CONTENT);
	}

}
