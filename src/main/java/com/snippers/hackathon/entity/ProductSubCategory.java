/**
 * 
 */
package com.snippers.hackathon.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

/**
 * @author rmamidala
 *
 */
@Entity
@Table(name = "PRODUCT_SUBCATEGORIES")
@Setter
@Getter
public class ProductSubCategory extends AuditModel {
	
	private static final long serialVersionUID = 4735918277139935811L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "SUBCATEGORY_NAME", nullable = false)
	private String subCategoryName;
	
	@Column(name = "SUBCATEGORY_DESCR")
	private String subCategoryDescr;
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH} ,fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "productCategory_Id", nullable = false)
	@JsonIgnore
	private ProductCategory productCategory;

}
