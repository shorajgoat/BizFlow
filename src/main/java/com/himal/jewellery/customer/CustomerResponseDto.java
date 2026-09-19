package com.himal.jewellery.customer;

import java.math.BigDecimal;

public class CustomerResponseDto {
    private Long id;
    private String name;
    private String phone;
    private String address;
    private BigDecimal dueAmount;

    public CustomerResponseDto(Customer c) {
        this.id = c.getId(); this.name = c.getName();
        this.phone = c.getPhone(); this.address = c.getAddress();
        this.dueAmount = c.getDueAmount();
    }
    public Long getId() { return id; }
    public String getName() { return name; }
    public String getPhone() { return phone; }
    public String getAddress() { return address; }
    public BigDecimal getDueAmount() { return dueAmount; }
}