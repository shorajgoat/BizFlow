package com.himal.jewellery.report;

import com.himal.jewellery.exception.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/reports")
public class ReportController {
    @Autowired
    private ReportService reportService;

    @GetMapping("/daily")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<SalesReportDto>> daily(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "Daily report", reportService.getDailyReport(date)));
    }

    @GetMapping("/monthly")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<SalesReportDto>> monthly(
            @RequestParam int year, @RequestParam int month) {
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "Monthly report", reportService.getMonthlyReport(year, month)));
    }
}