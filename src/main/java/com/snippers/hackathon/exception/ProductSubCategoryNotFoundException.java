/**
 * 
 */
package com.snippers.hackathon.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author rmamidala
 *
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductSubCategoryNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = -1847071511572194495L;

	public ProductSubCategoryNotFoundException(String message) {
		super(message);
	}

}
