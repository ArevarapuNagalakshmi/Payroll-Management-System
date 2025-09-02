package com.Payroll.Payroll.Management.System.service;

import com.Payroll.Payroll.Management.System.dto.EmployeeDTO;
import com.Payroll.Payroll.Management.System.entity.Department;
import com.Payroll.Payroll.Management.System.entity.Employee;
import com.Payroll.Payroll.Management.System.entity.Job;
import com.Payroll.Payroll.Management.System.entity.User;
import com.Payroll.Payroll.Management.System.repository.DepartmentRepository;
import com.Payroll.Payroll.Management.System.repository.EmployeeRepository;
import com.Payroll.Payroll.Management.System.repository.JobRepository;
import com.Payroll.Payroll.Management.System.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final UserRepository userRepository;
    private final DepartmentRepository departmentRepository;
    private final JobRepository jobRepository;

    public EmployeeService(EmployeeRepository employeeRepository,
                           UserRepository userRepository,
                           DepartmentRepository departmentRepository,
                           JobRepository jobRepository) {
        this.employeeRepository = employeeRepository;
        this.userRepository = userRepository;
        this.departmentRepository = departmentRepository;
        this.jobRepository = jobRepository;
    }

    // Create Employee
    public Employee createEmployee(EmployeeDTO dto) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + dto.getUserId()));

        Department department = departmentRepository.findById(dto.getDepartmentId())
                .orElseThrow(() -> new IllegalArgumentException("Department not found with id: " + dto.getDepartmentId()));

        Job job = jobRepository.findById(dto.getJobId())
                .orElseThrow(() -> new IllegalArgumentException("Job not found with id: " + dto.getJobId()));

        Employee emp = new Employee();
        emp.setUser(user);
        emp.setFirstName(dto.getFirstName());
        emp.setLastName(dto.getLastName());
        emp.setDob(dto.getDob());
        emp.setPhone(dto.getPhone());
        emp.setAddress(dto.getAddress());
        emp.setDepartment(department);
        emp.setDesignation(job);
        emp.setSalary(dto.getSalary());

        return employeeRepository.save(emp);
    }

    // Get all employees
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    // Get employee by ID
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Employee not found with id: " + id));
    }

    // Update employee
    public Employee updateEmployee(Long id, EmployeeDTO dto) {
        Employee emp = getEmployeeById(id);

        emp.setFirstName(dto.getFirstName());
        emp.setLastName(dto.getLastName());
        emp.setDob(dto.getDob());
        emp.setPhone(dto.getPhone());
        emp.setAddress(dto.getAddress());
        emp.setSalary(dto.getSalary());

        if (dto.getDepartmentId() != null) {
            Department dept = departmentRepository.findById(dto.getDepartmentId())
                    .orElseThrow(() -> new IllegalArgumentException("Department not found with id: " + dto.getDepartmentId()));
            emp.setDepartment(dept);
        }

        if (dto.getJobId() != null) {
            Job job = jobRepository.findById(dto.getJobId())
                    .orElseThrow(() -> new IllegalArgumentException("Job not found with id: " + dto.getJobId()));
            emp.setDesignation(job);
        }

        return employeeRepository.save(emp);
    }

    // Delete employee
    public void deleteEmployee(Long id) {
        Employee emp = getEmployeeById(id);
        employeeRepository.delete(emp);
    }
}
