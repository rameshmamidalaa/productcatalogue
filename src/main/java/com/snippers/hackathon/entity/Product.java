/**
 * 
 */
package com.snippers.hackathon.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author rmamidala
 *
 */
@Entity
@Table(name = "PRODUCT_CATALOGUE")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Product extends AuditModel {
	
	private static final long serialVersionUID = -717313085465910211L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "PRODUCT_CODE", nullable = false)
	private String productCode;
	
	@Column(name = "PRODUCT_NAME", nullable = false)
	private String productName;
	
	@Column(name = "PRODUCT_DESCR")
	private String productDescr;
	
	@Column(name = "IMAGEURL", nullable = false)
	private String imageUrl;
	
	@ElementCollection(fetch = FetchType.EAGER)
	@Column(name = "sizes", nullable = false)
	private List<Integer> sizes = new ArrayList<>();
	
	@Column(name = "price", nullable = false)
	private Double price;
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE}, fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "productSubCategory_Id", nullable = false)
	@JsonIgnore
	private ProductSubCategory productSubCategory;
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH} ,fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "productCategory_Id", nullable = false)
	@JsonIgnore
	private ProductCategory productCategory;


}
