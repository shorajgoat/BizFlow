package com.himal.jewellery.boxorder;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;

public class BoxOrderRequestDto {
    @NotBlank private String boxType;
    @NotNull @Positive private Integer quantity;
    @NotNull @Positive private BigDecimal costPerUnit;
    private String source;

    public String getBoxType() { return boxType; }
    public void setBoxType(String boxType) { this.boxType = boxType; }
    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
    public BigDecimal getCostPerUnit() { return costPerUnit; }
    public void setCostPerUnit(BigDecimal costPerUnit) { this.costPerUnit = costPerUnit; }
    public String getSource() { return source; }
    public void setSource(String source) { this.source = source; }
}