package com.himal.jewellery.expense;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ExpenseResponseDto {
    private Long id;
    private String title;
    private BigDecimal amount;
    private String category;
    private LocalDate expenseDate;

    public ExpenseResponseDto(Expense e) {
        this.id = e.getId(); this.title = e.getTitle();
        this.amount = e.getAmount(); this.category = e.getCategory();
        this.expenseDate = e.getExpenseDate();
    }
    public Long getId() { return id; }
    public String getTitle() { return title; }
    public BigDecimal getAmount() { return amount; }
    public String getCategory() { return category; }
    public LocalDate getExpenseDate() { return expenseDate; }
}