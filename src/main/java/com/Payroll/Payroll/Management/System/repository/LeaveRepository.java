package com.Payroll.Payroll.Management.System.repository;

import com.Payroll.Payroll.Management.System.entity.Leave;
import com.Payroll.Payroll.Management.System.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeaveRepository extends JpaRepository<Leave, Long> {

    List<Leave> findByEmployee(Employee employee);

    List<Leave> findByStatus(String status);
}
