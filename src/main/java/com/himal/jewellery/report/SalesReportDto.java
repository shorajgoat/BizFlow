package com.himal.jewellery.report;

import java.math.BigDecimal;

public class SalesReportDto {
    private BigDecimal totalSales;
    private Long totalTransactions;
    private BigDecimal totalExpenses;
    private BigDecimal netProfit;

    public SalesReportDto(BigDecimal totalSales, Long totalTransactions, BigDecimal totalExpenses) {
        this.totalSales = totalSales != null ? totalSales : BigDecimal.ZERO;
        this.totalTransactions = totalTransactions != null ? totalTransactions : 0L;
        this.totalExpenses = totalExpenses != null ? totalExpenses : BigDecimal.ZERO;
        this.netProfit = this.totalSales.subtract(this.totalExpenses);
    }
    public BigDecimal getTotalSales() { return totalSales; }
    public Long getTotalTransactions() { return totalTransactions; }
    public BigDecimal getTotalExpenses() { return totalExpenses; }
    public BigDecimal getNetProfit() { return netProfit; }
}