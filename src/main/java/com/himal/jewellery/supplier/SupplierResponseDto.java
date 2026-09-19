package com.himal.jewellery.supplier;

public class SupplierResponseDto {
    private Long id;
    private String name;
    private String phone;
    private String country;
    private String address;

    public SupplierResponseDto(Supplier s) {
        this.id = s.getId(); this.name = s.getName();
        this.phone = s.getPhone(); this.country = s.getCountry(); this.address = s.getAddress();
    }
    public Long getId() { return id; }
    public String getName() { return name; }
    public String getPhone() { return phone; }
    public String getCountry() { return country; }
    public String getAddress() { return address; }
}