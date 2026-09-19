package com.himal.jewellery.supplier;

import com.himal.jewellery.exception.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/suppliers")
public class SupplierController {
    @Autowired
    private SupplierService supplierService;

    @GetMapping
    public ResponseEntity<ApiResponse<Page<SupplierResponseDto>>> getAll(Pageable pageable) {
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "Suppliers fetched", supplierService.getAllSuppliers(pageable)));
    }
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<SupplierResponseDto>> getById(@PathVariable Long id) {
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "Supplier fetched", supplierService.getSupplierById(id)));
    }
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<SupplierResponseDto>> create(@Valid @RequestBody SupplierRequestDto dto) {
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "Supplier created", supplierService.createSupplier(dto)));
    }
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<SupplierResponseDto>> update(@PathVariable Long id, @Valid @RequestBody SupplierRequestDto dto) {
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "Supplier updated", supplierService.updateSupplier(id, dto)));
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        supplierService.deleteSupplier(id);
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "Supplier deleted", null));
    }
}