package com.Payroll.Payroll.Management.System.controller;

import com.Payroll.Payroll.Management.System.dto.LeaveDTO;
import com.Payroll.Payroll.Management.System.entity.Leave;
import com.Payroll.Payroll.Management.System.service.LeaveService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/leaves")
public class LeaveController {

    private final LeaveService leaveService;

    public LeaveController(LeaveService leaveService) {
        this.leaveService = leaveService;
    }

    // Employee applies for leave
    @PostMapping
    public ResponseEntity<Leave> applyLeave(@RequestBody LeaveDTO dto) {
        return ResponseEntity.ok(leaveService.applyLeave(dto));
    }

    // Admin approves/rejects leave
    @PatchMapping("/{leaveId}")
    public ResponseEntity<Leave> updateLeaveStatus(@PathVariable Long leaveId,
                                                   @RequestParam String status) {
        return ResponseEntity.ok(leaveService.updateLeaveStatus(leaveId, status));
    }

    // Employee view leave history
    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<Leave>> getLeavesByEmployee(@PathVariable Long employeeId) {
        return ResponseEntity.ok(leaveService.getLeavesByEmployee(employeeId));
    }

    // Admin view all pending leaves
    @GetMapping("/pending")
    public ResponseEntity<List<Leave>> getPendingLeaves() {
        return ResponseEntity.ok(leaveService.getPendingLeaves());
    }
}
