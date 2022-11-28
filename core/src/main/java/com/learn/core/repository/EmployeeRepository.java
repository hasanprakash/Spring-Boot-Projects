package com.learn.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learn.core.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    
}
