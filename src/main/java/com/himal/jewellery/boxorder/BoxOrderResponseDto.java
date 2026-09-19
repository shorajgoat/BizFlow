package com.himal.jewellery.boxorder;

import java.math.BigDecimal;
import java.time.LocalDate;

public class BoxOrderResponseDto {
    private Long id;
    private String boxType;
    private Integer quantity;
    private BigDecimal costPerUnit;
    private String source;
    private LocalDate orderDate;
    private String status;

    public BoxOrderResponseDto(BoxOrder b) {
        this.id = b.getId(); this.boxType = b.getBoxType();
        this.quantity = b.getQuantity(); this.costPerUnit = b.getCostPerUnit();
        this.source = b.getSource(); this.orderDate = b.getOrderDate();
        this.status = b.getStatus();
    }
    public Long getId() { return id; }
    public String getBoxType() { return boxType; }
    public Integer getQuantity() { return quantity; }
    public BigDecimal getCostPerUnit() { return costPerUnit; }
    public String getSource() { return source; }
    public LocalDate getOrderDate() { return orderDate; }
    public String getStatus() { return status; }
}