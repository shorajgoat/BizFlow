package com.himal.jewellery.customer;

import jakarta.validation.constraints.NotBlank;

public class CustomerRequestDto {
    @NotBlank(message = "Customer name is required")
    private String name;
    private String phone;
    private String address;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
}