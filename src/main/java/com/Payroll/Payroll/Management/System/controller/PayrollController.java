package com.Payroll.Payroll.Management.System.controller;

import com.Payroll.Payroll.Management.System.dto.PayrollDTO;
import com.Payroll.Payroll.Management.System.entity.Payroll;
import com.Payroll.Payroll.Management.System.service.PayrollService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/payroll")
public class PayrollController {

    private final PayrollService payrollService;

    public PayrollController(PayrollService payrollService) {
        this.payrollService = payrollService;
    }

    // Create payroll for employee
    @PostMapping("/runs")
    public ResponseEntity<Payroll> createPayroll(@RequestBody PayrollDTO dto) {
        return ResponseEntity.ok(payrollService.createPayroll(dto));
    }

    // Get payroll history for employee
    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<Payroll>> getPayrollHistory(@PathVariable Long employeeId) {
        return ResponseEntity.ok(payrollService.getPayrollByEmployee(employeeId));
    }

    // Get payroll report for a period
    @GetMapping("/report")
    public ResponseEntity<List<Payroll>> getPayrollReport(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end
    ) {
        return ResponseEntity.ok(payrollService.getPayrollByDateRange(start, end));
    }
}
