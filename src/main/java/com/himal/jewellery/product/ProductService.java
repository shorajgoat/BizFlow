
package com.himal.jewellery.product;

import com.himal.jewellery.category.Category;
import com.himal.jewellery.category.CategoryRepository;
import com.himal.jewellery.exception.CategoryNotFoundException;
import com.himal.jewellery.exception.ProductNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public Page<ProductResponseDto> searchProducts(
            String name,
            Long categoryId,
            BigDecimal minPrice,
            BigDecimal maxPrice,
            Pageable pageable) {

        Specification<Product> spec =
                ProductSpecification.hasName(name)
                        .and(ProductSpecification.hasCategoryId(categoryId))
                        .and(ProductSpecification.priceGreaterThanOrEqual(minPrice))
                        .and(ProductSpecification.priceLessThanOrEqual(maxPrice));

        Page<Product> products = productRepository.findAll(spec, pageable);

        return products.map(product -> new ProductResponseDto(product));
    }

    public Page<ProductResponseDto> getAllProducts(Pageable pageable) {

        Page<Product> products = productRepository.findAll(pageable);

        return products.map(product -> new ProductResponseDto(product));
    }

    public ProductResponseDto getProductById(Long id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() ->
                        new ProductNotFoundException(
                                "Product not found with id: " + id));

        return new ProductResponseDto(product);
    }

    public ProductResponseDto createProduct(ProductRequestDto dto) {

        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() ->
                        new CategoryNotFoundException(
                                "Category not found with id: " + dto.getCategoryId()));

        Product product = new Product(
                dto.getName(),
                dto.getPurchasePrice(),
                dto.getPrice(),
                dto.getStock(),
                dto.getMinStock(),
                dto.getSource(),
                category
        );

        Product saved = productRepository.save(product);

        return new ProductResponseDto(saved);
    }

    public ProductResponseDto updateProduct(Long id, ProductRequestDto dto) {

        Product product = productRepository.findById(id)
                .orElseThrow(() ->
                        new ProductNotFoundException(
                                "Product not found with id: " + id));

        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() ->
                        new CategoryNotFoundException(
                                "Category not found with id: " + dto.getCategoryId()));

        product.setName(dto.getName());
        product.setPurchasePrice(dto.getPurchasePrice());
        product.setPrice(dto.getPrice());
        product.setStock(dto.getStock());
        product.setMinStock(dto.getMinStock());
        product.setSource(dto.getSource());
        product.setCategory(category);

        Product updated = productRepository.save(product);

        return new ProductResponseDto(updated);
    }

    public void deleteProduct(Long id) {

        if (!productRepository.existsById(id)) {
            throw new ProductNotFoundException(
                    "Product not found with id: " + id);
        }

        productRepository.deleteById(id);
    }

    public List<String> getAllProductNames() {

        return productRepository.findAll()
                .stream()
                .map(product -> product.getName())
                .collect(Collectors.toList());
    }

    public List<Product> getLowStockProducts() {

        return productRepository.findAll()
                .stream()
                .filter(product -> product.getStock() < product.getMinStock())
                .collect(Collectors.toList());
    }
}

