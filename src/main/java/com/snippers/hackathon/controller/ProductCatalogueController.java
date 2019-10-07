/**
 * 
 */
package com.snippers.hackathon.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.snippers.hackathon.model.ProductDTO;
import com.snippers.hackathon.service.ProductCatalogueService;

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
@Api(tags = {"Operations pertaining to ProductCatalogue Details"})
public class ProductCatalogueController {
	
	@Autowired
	private ProductCatalogueService service;
	
	
	@ApiOperation(value = "Retrieve available ProductCatealogue Details")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully retrieved the list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 401, message = "The resource you were trying to reach is not found")
	})
	@GetMapping("/productCategories/{productCategoryId}/ProductSubCategories/{productSubCategoryId}/productCatalogue")
	public ResponseEntity<List<ProductDTO>> getProductCatalogue(
			@ApiParam(value = "ProductCategory Id which will associate with the ProductCatalogue Object", required = true) @PathVariable("productCategoryId") Long productCategoryId,
			@ApiParam(value = "ProductSubCategory Id which will associate with the ProductCatalogue Object", required = true) @PathVariable("productSubCategoryId") Long productSubCategoryId) {
		log.info("Fetching the available ProductCatalogue::");
		List<ProductDTO> productCatalogue = service.getProductCatalogue(productCategoryId, productSubCategoryId);
		if(productCatalogue.isEmpty()) {
			return new ResponseEntity<List<ProductDTO>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<ProductDTO>>(productCatalogue, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Create the ProductCatalogue and persist in the database table.")
	@PostMapping("/productCategories/{productCategoryId}/ProductSubCategories/{productSubCategoryId}/productCatalogue")
	public ResponseEntity<String> createProductCatalogue(
			@ApiParam(value = "ProductCategory Id which should associate with the ProductCatalogue Object", required = true) @PathVariable("productCategoryId") Long productCategoryId,
			@ApiParam(value = "ProductSubCategory Id which should associate with the ProductCatalogue Object", required = true) @PathVariable("productSubCategoryId") Long productSubCategoryId,
			@ApiParam(value = "List of Product Objects which stores in the database table.", required = true) @Valid @RequestBody List<ProductDTO> productCatalogue) {
		service.createProductCatalogue(productCategoryId, productSubCategoryId, productCatalogue);
		return new ResponseEntity<String>("New ProductCatalogue Info successfully created:::", HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "Search the product by using Productcode/ProductName/ProductDescr only.")
	@GetMapping("/productCatalogue")
	public ResponseEntity<List<ProductDTO>> searchProducts(
			@ApiParam(value = "List of Product Objects which stores in the database table.", required = true) @Valid @RequestBody ProductDTO productDTO) {
		List<ProductDTO> productCatalogue = service.searchProducts(productDTO);
		if(productCatalogue.isEmpty()) {
			return new ResponseEntity<List<ProductDTO>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<ProductDTO>>(productCatalogue, HttpStatus.OK);
	}

}
