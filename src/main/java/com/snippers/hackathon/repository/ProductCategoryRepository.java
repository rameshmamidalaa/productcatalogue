/**
 * 
 */
package com.snippers.hackathon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.snippers.hackathon.entity.ProductCategory;

/**
 * @author rmamidala
 *
 */
@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {

}
