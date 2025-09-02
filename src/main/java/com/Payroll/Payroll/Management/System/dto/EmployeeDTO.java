package com.Payroll.Payroll.Management.System.dto;

import java.time.LocalDate;

public class EmployeeDTO {

    private Long userId;
    private String firstName;
    private String lastName;
    private LocalDate dob;
    private String phone;
    private String address;
    private Long departmentId;
    private Long jobId;
    private Double salary;

    // Getters & Setters
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public LocalDate getDob() { return dob; }
    public void setDob(LocalDate dob) { this.dob = dob; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public Long getDepartmentId() { return departmentId; }
    public void setDepartmentId(Long departmentId) { this.departmentId = departmentId; }

    public Long getJobId() { return jobId; }
    public void setJobId(Long jobId) { this.jobId = jobId; }

    public Double getSalary() { return salary; }
    public void setSalary(Double salary) { this.salary = salary; }
}

