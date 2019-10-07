/**
 * 
 */
package com.snippers.hackathon.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author rmamidala
 *
 */
@Entity
@Table(name = "PRODUCT_CATEGORIES")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductCategory extends AuditModel {
	
	private static final long serialVersionUID = 5772922751203110460L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "CATEGORY_NAME", nullable = false)
	private String categoryName;
	
	@Column(name = "CATEGORY_DESCR")
	private String categoryDescr;

}
