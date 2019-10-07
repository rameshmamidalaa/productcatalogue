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
public class ProductCategoryDTO implements Serializable {
	
	private static final long serialVersionUID = 8430659612642741923L;
	
	@JsonIgnore
	private Long categoryId;
	private String categoryName;
	private String categoryDescr;

}
