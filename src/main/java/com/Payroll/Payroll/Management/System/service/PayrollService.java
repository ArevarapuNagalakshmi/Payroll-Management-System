package com.Payroll.Payroll.Management.System.service;

import com.Payroll.Payroll.Management.System.dto.PayrollDTO;
import com.Payroll.Payroll.Management.System.entity.Employee;
import com.Payroll.Payroll.Management.System.entity.Payroll;
import com.Payroll.Payroll.Management.System.repository.EmployeeRepository;
import com.Payroll.Payroll.Management.System.repository.PayrollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PayrollService {

    private final PayrollRepository payrollRepository;
    private final EmployeeRepository employeeRepository;

    @Autowired
    public PayrollService(PayrollRepository payrollRepository, EmployeeRepository employeeRepository) {
        this.payrollRepository = payrollRepository;
        this.employeeRepository = employeeRepository;
    }

    // Create a payroll run for an employee
    public Payroll createPayroll(PayrollDTO dto) {
        Employee emp = employeeRepository.findById(dto.getEmployeeId())
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + dto.getEmployeeId()));

        Payroll payroll = new Payroll();
        payroll.setEmployee(emp);
        payroll.setBasicSalary(dto.getBasicSalary());
        payroll.setDeductions(dto.getDeductions() != null ? dto.getDeductions() : 0.0);
        payroll.setBonus(dto.getBonus() != null ? dto.getBonus() : 0.0);
        payroll.setNetSalary(
                payroll.getBasicSalary() - payroll.getDeductions() + payroll.getBonus()
        );
        payroll.setPayDate(dto.getPayDate() != null ? dto.getPayDate() : LocalDate.now());

        return payrollRepository.save(payroll);
    }

    // Get payroll history of an employee
    public List<Payroll> getPayrollByEmployee(Long employeeId) {
        Employee emp = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + employeeId));
        return payrollRepository.findByEmployee(emp);
    }

    // Get payroll by date range (for reports)
    public List<Payroll> getPayrollByDateRange(LocalDate start, LocalDate end) {
        return payrollRepository.findByPayDateBetween(start, end);
    }
}
