package com.tech.controller;

import com.tech.dto.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "employee")
public class EmployeeController {
    @Autowired
    private Employee employee;

    @GetMapping
    public Employee getEmployee() {
        Employee employee = new Employee();
        employee.setEmail(this.employee.getEmail());
        employee.setId(this.employee.getId());
        employee.setName(this.employee.getName());
        return employee;
    }
}
