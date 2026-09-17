package com.himal.jewellery.category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.himal.jewellery.exception.ApiResponse;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
	@Autowired
	private CategoryService categoryService;
	@GetMapping
	public ResponseEntity<ApiResponse<Page<CategoryResponseDto>>> getAllCategories(Pageable pageable) {
	    Page<CategoryResponseDto> categories = categoryService.getAllCategories(pageable);
	    return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "Categories fetched", categories));
	}
	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<CategoryResponseDto>>getCategoryById(@PathVariable Long id){
		CategoryResponseDto dto=categoryService.getCategoryById(id);
		ApiResponse<CategoryResponseDto>response=new ApiResponse<>(HttpStatus.OK.value(),"Category Fetched",dto);
		return ResponseEntity.ok(response);
	}
	
	@PostMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<ApiResponse<CategoryResponseDto>>createCategory(@RequestBody CategoryRequestDto dto){
		CategoryResponseDto created=categoryService.createCategory(dto);
		ApiResponse<CategoryResponseDto> response=new ApiResponse<>(HttpStatus.CREATED.value(),"New Category Created",created);
		return ResponseEntity.ok(response);
	}
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<ApiResponse<Void>>deleteCategory(@PathVariable Long id){
		categoryService.deleteCategory(id);
		ApiResponse<Void>response=new ApiResponse<>(HttpStatus.OK.value(),"Category Deleted Successfully",null);
		return ResponseEntity.ok(response);
	}
	
}

