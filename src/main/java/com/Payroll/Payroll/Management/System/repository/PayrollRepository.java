package com.Payroll.Payroll.Management.System.repository;

import com.Payroll.Payroll.Management.System.entity.Employee;
import com.Payroll.Payroll.Management.System.entity.Payroll;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface PayrollRepository extends JpaRepository<Payroll, Long> {

    // Get all payrolls of a specific employee
    List<Payroll> findByEmployee(Employee employee);

    // Get payrolls within a date range
    List<Payroll> findByPayDateBetween(LocalDate start, LocalDate end);
}
