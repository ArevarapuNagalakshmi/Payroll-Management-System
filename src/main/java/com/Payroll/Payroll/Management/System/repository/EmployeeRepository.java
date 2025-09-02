package com.Payroll.Payroll.Management.System.repository;

import com.Payroll.Payroll.Management.System.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    // Find employee by the associated User ID
    Optional<Employee> findByUserUserId(Long userId);
}
