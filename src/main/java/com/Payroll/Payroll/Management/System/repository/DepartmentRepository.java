package com.Payroll.Payroll.Management.System.repository;

import com.Payroll.Payroll.Management.System.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    // You can add custom queries here if needed, e.g.,
    // Optional<Department> findByName(String name);
}
