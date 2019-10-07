/**
 * 
 */
package com.snippers.hackathon.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author rmamidala
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO implements Serializable {

	private static final long serialVersionUID = 7540427244572044566L;
	
	private String productName;
	private String productDescr;
	private String productCode;
	private String imageUrl;
	private List<Integer> sizes;
	private Double price;
	@JsonIgnore
	private ProductCategoryDTO productCategoryDTO;
	@JsonIgnore
	private ProductSubCategoryDTO productSubCategoryDTO;
}
