/**
 * 
 */
package com.snippers.hackathon.exception;

import java.util.Date;

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
public class ErrorDetails {
	
	private Date timestamp;
	private String message;
	private String details;

}

