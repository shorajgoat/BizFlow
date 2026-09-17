package com.himal.jewellery.category;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.himal.jewellery.exception.CategoryNotFoundException;

@Service
public class CategoryService {
@Autowired
private CategoryRepository categoryRepo;

public Page<CategoryResponseDto> getAllCategories(Pageable pageable) {
    Page<Category> categories = categoryRepo.findAll(pageable);
    return categories.map(category -> new CategoryResponseDto(category));
}
public CategoryResponseDto getCategoryById(Long id) {
	Category category=categoryRepo.findById(id).orElseThrow(()
			->new CategoryNotFoundException("Category Couldnot be found"));
	return new CategoryResponseDto(category);
}
public CategoryResponseDto createCategory(CategoryRequestDto dto) {
	Category category=new Category(dto.getName());
	Category saved=categoryRepo.save(category);
	return new CategoryResponseDto(saved);
}
public void deleteCategory(Long id) {
	if(!categoryRepo.existsById(id)) {
		throw new CategoryNotFoundException("This category Doesnt Exist");
	}
	categoryRepo.deleteById(id);
	
}
}
 