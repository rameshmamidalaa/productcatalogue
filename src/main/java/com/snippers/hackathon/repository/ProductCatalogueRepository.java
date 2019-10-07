/**
 * 
 */
package com.snippers.hackathon.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.snippers.hackathon.entity.Product;

/**
 * @author rmamidala
 *
 */
@Repository
public interface ProductCatalogueRepository extends JpaRepository<Product, Long> {
	
	@Query("SELECT p FROM Product p JOIN ProductSubCategory ps ON p.productCategory = ps.productCategory AND p.productSubCategory = ps.id "
			+ "WHERE p.productCategory.id = :productCategory_Id AND p.productSubCategory.id = :productSubCategory_Id")
	List<Product> findProductByCategories(@Param("productCategory_Id") Long productCategory_Id, 
			@Param("productSubCategory_Id") Long productSubCategory_Id);
	
	@Query("SELECT p FROM Product p WHERE "+
	"LOWER(p.productCode) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR "+
	"LOWER(productName) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR "+
	"LOWER(productDescr) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
	List<Product> findProductBySearchTerm(@Param("searchTerm") String searchTerm);

}
