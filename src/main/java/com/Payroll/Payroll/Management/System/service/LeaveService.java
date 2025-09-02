package com.Payroll.Payroll.Management.System.service;

import com.Payroll.Payroll.Management.System.dto.LeaveDTO;
import com.Payroll.Payroll.Management.System.entity.Employee;
import com.Payroll.Payroll.Management.System.entity.Leave;
import com.Payroll.Payroll.Management.System.repository.EmployeeRepository;
import com.Payroll.Payroll.Management.System.repository.LeaveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaveService {

    private final LeaveRepository leaveRepository;
    private final EmployeeRepository employeeRepository;

    @Autowired
    public LeaveService(LeaveRepository leaveRepository, EmployeeRepository employeeRepository) {
        this.leaveRepository = leaveRepository;
        this.employeeRepository = employeeRepository;
    }

    // Apply for leave
    public Leave applyLeave(LeaveDTO dto) {
        Employee emp = employeeRepository.findById(dto.getEmployeeId())
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + dto.getEmployeeId()));

        Leave leave = new Leave();
        leave.setEmployee(emp);
        leave.setStartDate(dto.getStartDate());
        leave.setEndDate(dto.getEndDate());
        leave.setLeaveType(dto.getLeaveType());
        leave.setStatus("Pending");

        return leaveRepository.save(leave);
    }

    // Approve or reject leave (Admin)
    public Leave updateLeaveStatus(Long leaveId, String status) {
        Leave leave = leaveRepository.findById(leaveId)
                .orElseThrow(() -> new RuntimeException("Leave not found with id: " + leaveId));

        leave.setStatus(status);
        return leaveRepository.save(leave);
    }

    // Get leaves of an employee
    public List<Leave> getLeavesByEmployee(Long employeeId) {
        Employee emp = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + employeeId));
        return leaveRepository.findByEmployee(emp);
    }

    // Get all pending leaves (Admin)
    public List<Leave> getPendingLeaves() {
        return leaveRepository.findByStatus("Pending");
    }
}
