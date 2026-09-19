package com.himal.jewellery.sale;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class SaleResponseDto {
    private Long id;
    private String customerName;
    private BigDecimal totalAmount;
    private String paymentMethod;
    private LocalDateTime saleDate;
    private List<SaleItemResponseDto> items;

    public SaleResponseDto(Sale sale) {
        this.id = sale.getId();
        this.customerName = sale.getCustomer() != null ? sale.getCustomer().getName() : "Walk-in";
        this.totalAmount = sale.getTotalAmount();
        this.paymentMethod = sale.getPaymentMethod();
        this.saleDate = sale.getSaleDate();
        this.items = sale.getItems().stream().map(i -> new SaleItemResponseDto(i)).collect(Collectors.toList());
    }
    public Long getId() { return id; }
    public String getCustomerName() { return customerName; }
    public BigDecimal getTotalAmount() { return totalAmount; }
    public String getPaymentMethod() { return paymentMethod; }
    public LocalDateTime getSaleDate() { return saleDate; }
    public List<SaleItemResponseDto> getItems() { return items; }
}