package com.himal.jewellery.product;

import java.math.BigDecimal;

public class ProductResponseDto {

    private Long id;
    private String name;
    private BigDecimal purchasePrice;
    private BigDecimal price;
    private Integer stock;
    private Integer minStock;
    private String source;
    private Long categoryId;
    private String categoryName;

    public ProductResponseDto(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.purchasePrice = product.getPurchasePrice();
        this.price = product.getPrice();
        this.stock = product.getStock();
        this.minStock = product.getMinStock();
        this.source = product.getSource();
        if (product.getCategory() != null) {
            this.categoryId = product.getCategory().getId();
            this.categoryName = product.getCategory().getName();
        }
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public BigDecimal getPurchasePrice() { return purchasePrice; }
    public BigDecimal getPrice() { return price; }
    public Integer getStock() { return stock; }
    public Integer getMinStock() { return minStock; }
    public String getSource() { return source; }
    public Long getCategoryId() { return categoryId; }
    public String getCategoryName() { return categoryName; }
}