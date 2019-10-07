/**
 * 
 */
package com.snippers.hackathon.model;

import java.io.Serializable;

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
public class ProductSubCategoryDTO implements Serializable {
	
	private static final long serialVersionUID = 4995672569848942357L;
	
	@JsonIgnore
	private Long subCategoryId;
	private String subCategoryName;
	private String subCategoryDescr;
	@JsonIgnore
	private ProductCategoryDTO productCategoryDTO;
	
	

}
