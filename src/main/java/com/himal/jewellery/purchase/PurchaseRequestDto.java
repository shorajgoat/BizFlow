package com.himal.jewellery.purchase;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.List;

public class PurchaseRequestDto {
    @NotNull(message = "Supplier is required")
    private Long supplierId;
    @NotEmpty(message = "At least one item is required")
    @Valid
    private List<PurchaseItemRequestDto> items;

    public Long getSupplierId() { return supplierId; }
    public void setSupplierId(Long supplierId) { this.supplierId = supplierId; }
    public List<PurchaseItemRequestDto> getItems() { return items; }
    public void setItems(List<PurchaseItemRequestDto> items) { this.items = items; }
}