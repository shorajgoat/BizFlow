package com.himal.jewellery.sale;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;

public class SaleRequestDto {
    private Long customerId; // optional — walk-in sale if null
    @NotBlank(message = "Payment method is required")
    private String paymentMethod;
    @NotEmpty(message = "At least one item is required")
    @Valid
    private List<SaleItemRequestDto> items;

    public Long getCustomerId() { return customerId; }
    public void setCustomerId(Long customerId) { this.customerId = customerId; }
    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }
    public List<SaleItemRequestDto> getItems() { return items; }
    public void setItems(List<SaleItemRequestDto> items) { this.items = items; }
}