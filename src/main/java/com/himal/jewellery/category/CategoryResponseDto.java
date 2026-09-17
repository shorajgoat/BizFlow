package com.himal.jewellery.category;

public class CategoryResponseDto {

    private Long id;
    private String name;

    public CategoryResponseDto(Category category) {
        this.id = category.getId();
        this.name = category.getName();
    }

    public Long getId() { return id; }
    public String getName() { return name; }
}