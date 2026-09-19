package com.himal.jewellery.supplier;

import jakarta.validation.constraints.NotBlank;

public class SupplierRequestDto {
    @NotBlank(message = "Supplier name is required")
    private String name;
    private String phone;
    private String country;
    private String address;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
}