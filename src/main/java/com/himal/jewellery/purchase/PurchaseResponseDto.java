package com.himal.jewellery.purchase;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class PurchaseResponseDto {
    private Long id;
    private String supplierName;
    private BigDecimal totalAmount;
    private LocalDateTime purchaseDate;
    private List<PurchaseItemResponseDto> items;

    public PurchaseResponseDto(Purchase p) {
        this.id = p.getId();
        this.supplierName = p.getSupplier().getName();
        this.totalAmount = p.getTotalAmount();
        this.purchaseDate = p.getPurchaseDate();
        this.items = p.getItems().stream().map(i -> new PurchaseItemResponseDto(i)).collect(Collectors.toList());
    }
    public Long getId() { return id; }
    public String getSupplierName() { return supplierName; }
    public BigDecimal getTotalAmount() { return totalAmount; }
    public LocalDateTime getPurchaseDate() { return purchaseDate; }
    public List<PurchaseItemResponseDto> getItems() { return items; }
}