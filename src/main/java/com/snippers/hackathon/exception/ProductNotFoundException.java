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
public class ProductNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1816412837057506285L;

	public ProductNotFoundException(String message) {
		super(message);
	}

}
