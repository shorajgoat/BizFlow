package com.himal.jewellery.supplier;

import com.himal.jewellery.exception.SupplierNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SupplierService {
    @Autowired
    private SupplierRepository supplierRepository;

    public Page<SupplierResponseDto> getAllSuppliers(Pageable pageable) {
        return supplierRepository.findAll(pageable).map(s -> new SupplierResponseDto(s));
    }
    public SupplierResponseDto getSupplierById(Long id) {
        Supplier s = supplierRepository.findById(id)
                .orElseThrow(() -> new SupplierNotFoundException("Supplier not found with id: " + id));
        return new SupplierResponseDto(s);
    }
    public SupplierResponseDto createSupplier(SupplierRequestDto dto) {
        Supplier s = new Supplier(dto.getName(), dto.getPhone(), dto.getCountry(), dto.getAddress());
        return new SupplierResponseDto(supplierRepository.save(s));
    }
    public SupplierResponseDto updateSupplier(Long id, SupplierRequestDto dto) {
        Supplier s = supplierRepository.findById(id)
                .orElseThrow(() -> new SupplierNotFoundException("Supplier not found with id: " + id));
        s.setName(dto.getName()); s.setPhone(dto.getPhone());
        s.setCountry(dto.getCountry()); s.setAddress(dto.getAddress());
        return new SupplierResponseDto(supplierRepository.save(s));
    }
    public void deleteSupplier(Long id) {
        if (!supplierRepository.existsById(id))
            throw new SupplierNotFoundException("Supplier not found with id: " + id);
        supplierRepository.deleteById(id);
    }
}