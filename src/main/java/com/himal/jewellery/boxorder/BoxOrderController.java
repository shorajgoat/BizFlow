package com.himal.jewellery.boxorder;

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
@RequestMapping("/api/box-orders")
public class BoxOrderController {
    @Autowired
    private BoxOrderService boxOrderService;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','STAFF')")
    public ResponseEntity<ApiResponse<Page<BoxOrderResponseDto>>> getAll(Pageable pageable) {
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "Box orders fetched", boxOrderService.getAll(pageable)));
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','STAFF')")
    public ResponseEntity<ApiResponse<BoxOrderResponseDto>> getById(@PathVariable Long id) {
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "Box order fetched", boxOrderService.getById(id)));
    }
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<BoxOrderResponseDto>> create(@Valid @RequestBody BoxOrderRequestDto dto) {
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "Box order created", boxOrderService.create(dto)));
    }
    @PatchMapping("/{id}/status")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<BoxOrderResponseDto>> updateStatus(@PathVariable Long id, @RequestParam String status) {
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "Status updated", boxOrderService.updateStatus(id, status)));
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        boxOrderService.delete(id);
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "Box order deleted", null));
    }
}