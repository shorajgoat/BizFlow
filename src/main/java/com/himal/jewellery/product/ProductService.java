package com.himal.jewellery.product;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.himal.jewellery.exception.ProductNotFoundException;

@Service
public class ProductService {
@Autowired
private ProductRepository productRepo;

public List<ProductResponseDto>getAllProduct(){
	return productRepo.findAll().stream()
			.map(product->new ProductResponseDto(product))
			.collect(Collectors.toList());
}
public ProductResponseDto getProductById(Long id){
	Product product=productRepo.findById(id).orElseThrow(()
			->new ProductNotFoundException("Product of This Id doesnt Exist"));
	return new ProductResponseDto(product);
}

public ProductResponseDto createProduct(ProductRequestDto dto) {
	Product product=new Product
			(dto.getName(),dto.getPurchasePrice(),dto.getPrice(),dto.getStock(),dto.getMinStock(),dto.getSource());
	return new ProductResponseDto(productRepo.save(product));
}

public ProductResponseDto updateProduct(Long id,ProductRequestDto dto) {
	Product product=productRepo.findById(id).orElseThrow(()->new ProductNotFoundException("Product of this Id not found"));
	 product.setName(dto.getName());
     product.setPurchasePrice(dto.getPurchasePrice());
     product.setPrice(dto.getPrice());
     product.setStock(dto.getStock());
     product.setMinStock(dto.getMinStock());
     product.setSource(dto.getSource());
	return new ProductResponseDto(productRepo.save(product));
	
}

public void deleteProduct(Long id) {
	Product product=productRepo.findById(id).orElseThrow(()->new ProductNotFoundException("Product cant be found "));
	productRepo.delete(product);
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
