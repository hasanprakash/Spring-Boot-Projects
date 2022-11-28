package com.learn.core.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.learn.core.entity.Employee;
import com.learn.core.service.EmployeeDataService;

@RestController
public class EmployeeController {
    @Autowired
    private EmployeeDataService service;

    @PostMapping("/employee/save")
    public Employee saveEmployee(@RequestBody Employee employee) {
        return service.save(employee);
    }

    @GetMapping("employee/getAll")
    public List<Employee> getAllEmployees() {
        return service.getAll();
    }
}
