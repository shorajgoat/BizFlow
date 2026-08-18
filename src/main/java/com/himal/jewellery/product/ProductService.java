package com.himal.jewellery.product;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
@Autowired
private ProductRepository productRepo;
public Product saveProduct(Product product) {
	return productRepo.save(product);
}
public Optional<Product> getProductById(Long Id){
	return productRepo.findById(Id);
}


}
