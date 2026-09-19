package com.himal.jewellery.customer;

import com.himal.jewellery.exception.CustomerNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public Page<CustomerResponseDto> getAllCustomers(Pageable pageable) {
        return customerRepository.findAll(pageable).map(c -> new CustomerResponseDto(c));
    }
    public CustomerResponseDto getCustomerById(Long id) {
        Customer c = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found with id: " + id));
        return new CustomerResponseDto(c);
    }
    public CustomerResponseDto createCustomer(CustomerRequestDto dto) {
        Customer c = new Customer(dto.getName(), dto.getPhone(), dto.getAddress());
        return new CustomerResponseDto(customerRepository.save(c));
    }
    public CustomerResponseDto updateCustomer(Long id, CustomerRequestDto dto) {
        Customer c = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found with id: " + id));
        c.setName(dto.getName()); c.setPhone(dto.getPhone()); c.setAddress(dto.getAddress());
        return new CustomerResponseDto(customerRepository.save(c));
    }
    public void deleteCustomer(Long id) {
        if (!customerRepository.existsById(id))
            throw new CustomerNotFoundException("Customer not found with id: " + id);
        customerRepository.deleteById(id);
    }
    public CustomerResponseDto addDue(Long id, BigDecimal amount) {
        Customer c = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found with id: " + id));
        c.setDueAmount(c.getDueAmount().add(amount));
        return new CustomerResponseDto(customerRepository.save(c));
    }
    public CustomerResponseDto settleDue(Long id, BigDecimal amount) {
        Customer c = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found with id: " + id));
        c.setDueAmount(c.getDueAmount().subtract(amount));
        return new CustomerResponseDto(customerRepository.save(c));
    }
}