/**
 * 
 */
package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.Product;



/**
 * @author Harini
 *
 */
public interface ProductRepository extends JpaRepository<Product,Long> {
    Product findByProductId(int id);

    List<Product> findTop10ByProductNameIgnoreCaseContaining(String productName);

    boolean existsProductByProductName(String productName);
}
