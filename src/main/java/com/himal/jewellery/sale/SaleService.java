package com.himal.jewellery.sale;

import com.himal.jewellery.customer.Customer;
import com.himal.jewellery.customer.CustomerRepository;
import com.himal.jewellery.exception.InsufficientStockException;
import com.himal.jewellery.exception.ProductNotFoundException;
import com.himal.jewellery.exception.SaleNotFoundException;
import com.himal.jewellery.product.Product;
import com.himal.jewellery.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class SaleService {

    @Autowired private SaleRepository saleRepository;
    @Autowired private ProductRepository productRepository;
    @Autowired private CustomerRepository customerRepository;

    @Transactional
    public SaleResponseDto completeSale(SaleRequestDto dto) {

        Customer customer = null;
        if (dto.getCustomerId() != null) {
            customer = customerRepository.findById(dto.getCustomerId())
                    .orElseThrow(() -> new ProductNotFoundException("Customer not found with id: " + dto.getCustomerId()));
        }

        // Step 1: validate stock BEFORE making any changes
        List<Product> lockedProducts = new ArrayList<>();
        for (SaleItemRequestDto itemDto : dto.getItems()) {
            Product product = productRepository.findById(itemDto.getProductId())
                    .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + itemDto.getProductId()));
            if (product.getStock() < itemDto.getQuantity()) {
                throw new InsufficientStockException(
                        "Insufficient stock for " + product.getName() + ". Available: " + product.getStock());
            }
            lockedProducts.add(product);
        }

        // Step 2: calculate total, build Sale + SaleItems, deduct stock
        BigDecimal total = BigDecimal.ZERO;
        Sale sale = new Sale(customer, BigDecimal.ZERO, dto.getPaymentMethod());

        for (int i = 0; i < dto.getItems().size(); i++) {
            SaleItemRequestDto itemDto = dto.getItems().get(i);
            Product product = lockedProducts.get(i);

            BigDecimal subtotal = product.getPrice().multiply(BigDecimal.valueOf(itemDto.getQuantity()));
            total = total.add(subtotal);

            SaleItem saleItem = new SaleItem(sale, product, itemDto.getQuantity(), product.getPrice());
            sale.getItems().add(saleItem);

            product.setStock(product.getStock() - itemDto.getQuantity());
            productRepository.save(product);
        }

        sale.setTotalAmount(total);
        Sale saved = saleRepository.save(sale); // cascade saves SaleItems too

        if (customer != null && "CREDIT".equalsIgnoreCase(dto.getPaymentMethod())) {
            customer.setDueAmount(customer.getDueAmount().add(total));
            customerRepository.save(customer);
        }

        return new SaleResponseDto(saved);
    }

    public SaleResponseDto getSaleById(Long id) {
        Sale sale = saleRepository.findById(id)
                .orElseThrow(() -> new SaleNotFoundException("Sale not found with id: " + id));
        return new SaleResponseDto(sale);
    }

    public Page<SaleResponseDto> getAllSales(Pageable pageable) {
        return saleRepository.findAll(pageable).map(s -> new SaleResponseDto(s));
    }
}