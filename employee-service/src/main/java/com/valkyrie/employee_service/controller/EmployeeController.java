package com.valkyrie.employee_service.controller;

import com.valkyrie.employee_service.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private EmployeeService service;
    @Autowired
    private void setService(EmployeeService service) {this.service = service;}


}
