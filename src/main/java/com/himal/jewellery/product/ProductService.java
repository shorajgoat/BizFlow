package com.himal.jewellery.product;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

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

public List<String>getAllProductName(){
	return productRepo.findAll().stream()
			.map(product->product.getName())
			.collect(Collectors.toList());
}

public List<Product>getLowStockProducts(){
	return productRepo.findAll().stream()
			.filter(product->product.getStock()<product.getMinStock())
			.collect(Collectors.toList());
}

}
