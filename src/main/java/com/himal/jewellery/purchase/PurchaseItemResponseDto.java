package com.himal.jewellery.purchase;

import java.math.BigDecimal;

public class PurchaseItemResponseDto {
    private Long productId;
    private String productName;
    private Integer quantity;
    private BigDecimal unitCost;

    public PurchaseItemResponseDto(PurchaseItem item) {
        this.productId = item.getProduct().getId();
        this.productName = item.getProduct().getName();
        this.quantity = item.getQuantity();
        this.unitCost = item.getUnitCost();
    }
    public Long getProductId() { return productId; }
    public String getProductName() { return productName; }
    public Integer getQuantity() { return quantity; }
    public BigDecimal getUnitCost() { return unitCost; }
}