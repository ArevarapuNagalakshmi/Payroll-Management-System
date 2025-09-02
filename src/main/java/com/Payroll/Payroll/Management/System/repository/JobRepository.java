package com.Payroll.Payroll.Management.System.repository;

import com.Payroll.Payroll.Management.System.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {
    // Optional: add custom queries if needed, e.g.,
    // Optional<Job> findByTitle(String title);
}
