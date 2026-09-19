package com.himal.jewellery.boxorder;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "box_orders")
public class BoxOrder {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String boxType;
    @Column(nullable = false)
    private Integer quantity;
    @Column(nullable = false)
    private BigDecimal costPerUnit;
    private String source;
    @Column(nullable = false)
    private LocalDate orderDate;
    @Column(nullable = false)
    private String status; // PENDING, RECEIVED, CANCELLED

    public BoxOrder() {}
    public BoxOrder(String boxType, Integer quantity, BigDecimal costPerUnit, String source) {
        this.boxType = boxType; this.quantity = quantity;
        this.costPerUnit = costPerUnit; this.source = source;
        this.orderDate = LocalDate.now();
        this.status = "PENDING";
    }
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getBoxType() { return boxType; }
    public void setBoxType(String boxType) { this.boxType = boxType; }
    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
    public BigDecimal getCostPerUnit() { return costPerUnit; }
    public void setCostPerUnit(BigDecimal costPerUnit) { this.costPerUnit = costPerUnit; }
    public String getSource() { return source; }
    public void setSource(String source) { this.source = source; }
    public LocalDate getOrderDate() { return orderDate; }
    public void setOrderDate(LocalDate orderDate) { this.orderDate = orderDate; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}