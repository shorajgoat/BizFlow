package com.himal.jewellery.report;

import com.himal.jewellery.expense.ExpenseRepository;
import com.himal.jewellery.sale.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Service
public class ReportService {

    @Autowired private SaleRepository saleRepository;
    @Autowired private ExpenseRepository expenseRepository;

    public SalesReportDto getDailyReport(LocalDate date) {
        LocalDateTime start = LocalDateTime.of(date, LocalTime.MIN);
        LocalDateTime end = LocalDateTime.of(date, LocalTime.MAX);

        return new SalesReportDto(
                saleRepository.sumTotalSalesBetween(start, end),
                saleRepository.countSalesBetween(start, end),
                expenseRepository.sumTotalExpensesBetween(date, date)
        );
    }

    public SalesReportDto getMonthlyReport(int year, int month) {
        LocalDate startDate = LocalDate.of(year, month, 1);
        LocalDate endDate = startDate.withDayOfMonth(startDate.lengthOfMonth());
        LocalDateTime start = LocalDateTime.of(startDate, LocalTime.MIN);
        LocalDateTime end = LocalDateTime.of(endDate, LocalTime.MAX);

        return new SalesReportDto(
                saleRepository.sumTotalSalesBetween(start, end),
                saleRepository.countSalesBetween(start, end),
                expenseRepository.sumTotalExpensesBetween(startDate, endDate)
        );
    }
}