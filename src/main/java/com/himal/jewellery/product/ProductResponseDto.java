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

    public ProductResponseDto(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.purchasePrice = product.getPurchasePrice();
        this.price = product.getPrice();
        this.stock = product.getStock();
        this.minStock = product.getMinStock();
        this.source = product.getSource();
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public BigDecimal getPurchasePrice() { return purchasePrice; }
    public BigDecimal getPrice() { return price; }
    public Integer getStock() { return stock; }
    public Integer getMinStock() { return minStock; }
    public String getSource() { return source; }
}