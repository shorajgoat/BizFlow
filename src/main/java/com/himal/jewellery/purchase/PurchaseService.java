package com.himal.jewellery.purchase;

import com.himal.jewellery.exception.ProductNotFoundException;
import com.himal.jewellery.exception.PurchaseNotFoundException;
import com.himal.jewellery.exception.SupplierNotFoundException;
import com.himal.jewellery.product.Product;
import com.himal.jewellery.product.ProductRepository;
import com.himal.jewellery.supplier.Supplier;
import com.himal.jewellery.supplier.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class PurchaseService {
    @Autowired private PurchaseRepository purchaseRepository;
    @Autowired private ProductRepository productRepository;
    @Autowired private SupplierRepository supplierRepository;

    @Transactional
    public PurchaseResponseDto createPurchase(PurchaseRequestDto dto) {
        Supplier supplier = supplierRepository.findById(dto.getSupplierId())
                .orElseThrow(() -> new SupplierNotFoundException("Supplier not found with id: " + dto.getSupplierId()));

        BigDecimal total = BigDecimal.ZERO;
        Purchase purchase = new Purchase(supplier, BigDecimal.ZERO);

        for (PurchaseItemRequestDto itemDto : dto.getItems()) {
            Product product = productRepository.findById(itemDto.getProductId())
                    .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + itemDto.getProductId()));

            BigDecimal subtotal = itemDto.getUnitCost().multiply(BigDecimal.valueOf(itemDto.getQuantity()));
            total = total.add(subtotal);

            PurchaseItem item = new PurchaseItem(purchase, product, itemDto.getQuantity(), itemDto.getUnitCost());
            purchase.getItems().add(item);

            product.setStock(product.getStock() + itemDto.getQuantity()); // INCREASE stock
            productRepository.save(product);
        }

        purchase.setTotalAmount(total);
        Purchase saved = purchaseRepository.save(purchase);
        return new PurchaseResponseDto(saved);
    }

    public PurchaseResponseDto getPurchaseById(Long id) {
        Purchase p = purchaseRepository.findById(id)
                .orElseThrow(() -> new PurchaseNotFoundException("Purchase not found with id: " + id));
        return new PurchaseResponseDto(p);
    }

    public Page<PurchaseResponseDto> getAllPurchases(Pageable pageable) {
        return purchaseRepository.findAll(pageable).map(p -> new PurchaseResponseDto(p));
    }
}