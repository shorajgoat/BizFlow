package com.himal.jewellery.purchase;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class PurchaseItemRequestDto {
    @NotNull private Long productId;
    @NotNull @Positive private Integer quantity;
    @NotNull @Positive private java.math.BigDecimal unitCost;

    public Long getProductId() { return productId; }
    public void setProductId(Long productId) { this.productId = productId; }
    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
    public java.math.BigDecimal getUnitCost() { return unitCost; }
    public void setUnitCost(java.math.BigDecimal unitCost) { this.unitCost = unitCost; }
}