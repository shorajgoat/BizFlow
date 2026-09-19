package com.himal.jewellery.expense;

import com.himal.jewellery.exception.ExpenseNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ExpenseService {
    @Autowired
    private ExpenseRepository expenseRepository;

    public Page<ExpenseResponseDto> getAllExpenses(Pageable pageable) {
        return expenseRepository.findAll(pageable).map(e -> new ExpenseResponseDto(e));
    }
    public ExpenseResponseDto getExpenseById(Long id) {
        Expense e = expenseRepository.findById(id)
                .orElseThrow(() -> new ExpenseNotFoundException("Expense not found with id: " + id));
        return new ExpenseResponseDto(e);
    }
    public ExpenseResponseDto createExpense(ExpenseRequestDto dto) {
        Expense e = new Expense(dto.getTitle(), dto.getAmount(), dto.getCategory(), dto.getExpenseDate());
        return new ExpenseResponseDto(expenseRepository.save(e));
    }
    public ExpenseResponseDto updateExpense(Long id, ExpenseRequestDto dto) {
        Expense e = expenseRepository.findById(id)
                .orElseThrow(() -> new ExpenseNotFoundException("Expense not found with id: " + id));
        e.setTitle(dto.getTitle()); e.setAmount(dto.getAmount());
        e.setCategory(dto.getCategory()); e.setExpenseDate(dto.getExpenseDate());
        return new ExpenseResponseDto(expenseRepository.save(e));
    }
    public void deleteExpense(Long id) {
        if (!expenseRepository.existsById(id))
            throw new ExpenseNotFoundException("Expense not found with id: " + id);
        expenseRepository.deleteById(id);
    }
}