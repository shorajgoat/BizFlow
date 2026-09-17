package com.himal.jewellery.product;

import java.math.BigDecimal;
import jakarta.persistence.*;
import com.himal.jewellery.category.Category;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(name = "purchase_price", nullable = false)
    private BigDecimal purchasePrice;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private Integer stock;

    @Column(name = "min_stock")
    private Integer minStock;

    private String source;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public Product() {}

    public Product(String name, BigDecimal purchasePrice, BigDecimal price,
                    Integer stock, Integer minStock, String source, Category category) {
        this.name = name;
        this.purchasePrice = purchasePrice;
        this.price = price;
        this.stock = stock;
        this.minStock = minStock;
        this.source = source;
        this.category = category;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public BigDecimal getPurchasePrice() { return purchasePrice; }
    public void setPurchasePrice(BigDecimal purchasePrice) { this.purchasePrice = purchasePrice; }
    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }
    public Integer getStock() { return stock; }
    public void setStock(Integer stock) { this.stock = stock; }
    public Integer getMinStock() { return minStock; }
    public void setMinStock(Integer minStock) { this.minStock = minStock; }
    public String getSource() { return source; }
    public void setSource(String source) { this.source = source; }
    public Category getCategory() { return category; }
    public void setCategory(Category category) { this.category = category; }
}