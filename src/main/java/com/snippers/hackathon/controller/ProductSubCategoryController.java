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

import com.snippers.hackathon.model.ProductSubCategoryDTO;
import com.snippers.hackathon.service.ProductSubCategoryService;

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
@Api(tags = "Operations pertaining to ProductSubCategory Details")
public class ProductSubCategoryController {
	
	@Autowired
	private ProductSubCategoryService service;
	
	@ApiOperation(value = "Retrieve available ProductSubCategory Details")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully retrieved the list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 401, message = "The resource you were trying to reach is not found")
	})
	@GetMapping("/productCategories/{productCategoryId}/ProductSubCategories")
	public ResponseEntity<List<ProductSubCategoryDTO>> getAllProductSubCategories(@PathVariable("productCategoryId") Long productCategoryId) {
		log.info("Fetching all the available ProductSubCategories::::");
		List<ProductSubCategoryDTO> productSubCategoryDTO = service.getAllProductSubCategoriesByCategoryId(productCategoryId);
		if(productSubCategoryDTO.isEmpty()) {
			log.error("No ProductSubCategories found::");
			return new ResponseEntity<List<ProductSubCategoryDTO>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<ProductSubCategoryDTO>>(productSubCategoryDTO, HttpStatus.OK);
	}
	
	
	@ApiOperation(value = "Create new ProductSubCategory Details by passing the ProductSubCategory Object")
	@PostMapping("/productCategories/{productCategoryId}/ProductSubCategories")
	public ResponseEntity<String> createProductSubCategory(
			@ApiParam(value = "ProductCategory Id which will associate with the ProductSubCategory Object", required = true) @PathVariable("productCategoryId") Long productCategoryId,
			@ApiParam(value = "ProductSubCategory Object will save in the database table", required = true) @Valid @RequestBody ProductSubCategoryDTO productSubCategoryDTO) {
		log.info("Saving the requested ProductSubCategory with the following details {}", productSubCategoryDTO);
		service.createProductSubCategory(productCategoryId, productSubCategoryDTO);
		return new ResponseEntity<String>("New ProductSubCategory Info created successfully with the id", HttpStatus.CREATED);
	}
	
	@PutMapping("/productCategories/{productCategoryId}/ProductSubCategories/{id}")
	public ResponseEntity<String> updateProductSubCategory(
			@ApiParam(value = "ProductCategory Id which will associate with the ProductSubCategory Object", required = true) @PathVariable("productCategoryId") Long productCategoryId,
			@ApiParam(value = "ProductSubCategory Id to update the ProductSubCategory Object", required = true) @PathVariable("id") Long id, 
			@ApiParam(value = "Update the ProductSubCategory Object", required = true) @Valid @RequestBody ProductSubCategoryDTO productSubCategoryDTO) {
		log.info("Fetching & Updating the requested ProductSubCategory with id {}", id);
		service.updateProductSubCategory(productCategoryId, id, productSubCategoryDTO);
		return new ResponseEntity<String>("ProductSubCategory Info successfully updated for the requested id:::\"+id", HttpStatus.OK);
	}
	
	@ApiOperation(value = "Delete the ProductSubCategory Details by passing the ProductSubCategory ID")
	@DeleteMapping("//productCategories/{productCategoryId}/ProductSubCategories/{id}")
	public ResponseEntity<String> deleteProductSubCategoryById(
			@ApiParam(value = "ProductCategory Id which will associate with the ProductSubCategory Object", required = true) @PathVariable("productCategoryId") Long productCategoryId,
			@ApiParam(value = "ProductSubCategory Id from which ProductCategory object will delete", required = true) @PathVariable Long id) {
		log.info("Deleting the requested ProductSubCategory with id {}", id);
		service.deleteProductSubCategoryById(productCategoryId, id);
		return new ResponseEntity<String>("ProductSubCategory Information successfully deleted with id::"+id, HttpStatus.NO_CONTENT);
	}

}
