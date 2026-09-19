package com.himal.jewellery.customer;

import com.himal.jewellery.exception.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping
    public ResponseEntity<ApiResponse<Page<CustomerResponseDto>>> getAll(Pageable pageable) {
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "Customers fetched", customerService.getAllCustomers(pageable)));
    }
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CustomerResponseDto>> getById(@PathVariable Long id) {
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "Customer fetched", customerService.getCustomerById(id)));
    }
    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN','STAFF')")
    public ResponseEntity<ApiResponse<CustomerResponseDto>> create(@Valid @RequestBody CustomerRequestDto dto) {
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "Customer created", customerService.createCustomer(dto)));
    }
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','STAFF')")
    public ResponseEntity<ApiResponse<CustomerResponseDto>> update(@PathVariable Long id, @Valid @RequestBody CustomerRequestDto dto) {
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "Customer updated", customerService.updateCustomer(id, dto)));
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "Customer deleted", null));
    }
    @PostMapping("/{id}/add-due")
    @PreAuthorize("hasAnyRole('ADMIN','STAFF')")
    public ResponseEntity<ApiResponse<CustomerResponseDto>> addDue(@PathVariable Long id, @RequestParam BigDecimal amount) {
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "Due added", customerService.addDue(id, amount)));
    }
    @PostMapping("/{id}/settle-due")
    @PreAuthorize("hasAnyRole('ADMIN','STAFF')")
    public ResponseEntity<ApiResponse<CustomerResponseDto>> settleDue(@PathVariable Long id, @RequestParam BigDecimal amount) {
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "Due settled", customerService.settleDue(id, amount)));
    }
}