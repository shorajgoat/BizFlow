package com.himal.jewellery.purchase;

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
@RequestMapping("/api/purchases")
public class PurchaseController {
    @Autowired
    private PurchaseService purchaseService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<PurchaseResponseDto>> create(@Valid @RequestBody PurchaseRequestDto dto) {
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "Purchase recorded", purchaseService.createPurchase(dto)));
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','STAFF')")
    public ResponseEntity<ApiResponse<PurchaseResponseDto>> getById(@PathVariable Long id) {
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "Purchase fetched", purchaseService.getPurchaseById(id)));
    }
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','STAFF')")
    public ResponseEntity<ApiResponse<Page<PurchaseResponseDto>>> getAll(Pageable pageable) {
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "Purchases fetched", purchaseService.getAllPurchases(pageable)));
    }
}