package com.himal.jewellery.purchase;

import com.himal.jewellery.product.Product;
import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "purchase_items")
public class PurchaseItem {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "purchase_id")
    private Purchase purchase;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(nullable = false)
    private Integer quantity;

    @Column(name = "unit_cost", nullable = false)
    private BigDecimal unitCost;

    public PurchaseItem() {}
    public PurchaseItem(Purchase purchase, Product product, Integer quantity, BigDecimal unitCost) {
        this.purchase = purchase; this.product = product;
        this.quantity = quantity; this.unitCost = unitCost;
    }
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Purchase getPurchase() { return purchase; }
    public void setPurchase(Purchase purchase) { this.purchase = purchase; }
    public Product getProduct() { return product; }
    public void setProduct(Product product) { this.product = product; }
    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
    public BigDecimal getUnitCost() { return unitCost; }
    public void setUnitCost(BigDecimal unitCost) { this.unitCost = unitCost; }
}