/**
 * 
 */
package com.snippers.hackathon.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.snippers.hackathon.entity.ProductSubCategory;

/**
 * @author rmamidala
 *
 */
@Repository
public interface ProductSubCategoryRepository extends JpaRepository<ProductSubCategory, Long> {
	
	List<ProductSubCategory> findByProductCategoryId(Long productCategoryId);
	Optional<ProductSubCategory> findByIdAndProductCategoryId(Long id, Long productCategoryId);

}
