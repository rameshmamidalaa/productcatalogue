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
public class ProductCategoryNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = -428599189673269142L;

	public ProductCategoryNotFoundException(String message) {
		super(message);
	}

}
