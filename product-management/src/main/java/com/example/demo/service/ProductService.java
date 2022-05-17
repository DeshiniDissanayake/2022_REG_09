/**
 * 
 */
package com.example.demo.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Product;
import com.example.demo.repository.ProductRepository;



/**
 * @author Harini
 *
 */

@Service
public class ProductService {
	
	
	 @Autowired
	    ProductRepository productRepository;

	    @Transactional
	    public Product createProduct(Product product) {  // Create product
	        return productRepository.save(product);
	    }

	    public List<Product> getAllProducts(){   // Get all products
	        return productRepository.findAll();
	    }

	    public Product getProductByProductId(int productId){ // Get product by product id
	        return productRepository.findByProductId(productId);
	    }

	    public String getProductNameByProductId(int productId){   // Get product name by product id
	        return productRepository.findByProductId(productId).getProductName();
	    }

	    public List<Product> productsSearchByName(String name){ // Search product by name
	        return productRepository.findTop10ByProductNameIgnoreCaseContaining(name);
	    }

	    public boolean isProductExist(String name){       // Check product is exist or not
	        return productRepository.existsProductByProductName(name);
	    }

}
