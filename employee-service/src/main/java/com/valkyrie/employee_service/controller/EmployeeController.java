package com.valkyrie.employee_service.controller;

import com.valkyrie.employee_service.model.Employee;
import com.valkyrie.employee_service.model.EmployeeWrapper;
import com.valkyrie.employee_service.model.Store;
import com.valkyrie.employee_service.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private EmployeeService service;
    @Autowired
    private void setService(EmployeeService service) {this.service = service;}

    @PostMapping("/add-employee")
    public ResponseEntity<String> save(@RequestParam String token,
                                       @RequestBody Employee employee) {
        Store<String> store = service.save(employee);
        return ResponseEntity.status(store.getStatus()).body(store.getInstance());
    }

    @PostMapping("/update-employee")
    public ResponseEntity<String> update(@RequestParam String token,
                                         @RequestParam String id,
                                         @RequestBody Employee employee) {
        employee = employee.setId(id);
        return save(token, employee);
    }

    @GetMapping("/find-employee-by-id")
    public ResponseEntity<EmployeeWrapper> employeeById(@RequestParam String id) {
        Store<EmployeeWrapper> store = service.findEmployeeById(id);
        return ResponseEntity.status(store.getStatus()).body(store.getInstance());
    }

    @GetMapping("/find-employee-by-job")
    public ResponseEntity<List<EmployeeWrapper>> employeesWithSameJobs(@RequestParam String jobId) {
        Store<List<EmployeeWrapper>> store = service.findEmployeeWithSameJob(jobId);
        return ResponseEntity.status(store.getStatus()).body(store.getInstance());
    }

    @DeleteMapping("/remove-employee-by-id")
    public ResponseEntity<String> removeEmployeeById(@RequestParam String id) {
        Store<String> store = service.removeEmployeeById(id);
        return ResponseEntity.status(store.getStatus()).body(store.getInstance());
    }

    @DeleteMapping("/remove-employee-by-jobs")
    public ResponseEntity<String> removeEmployeeByJobs(@RequestParam String jobId) {
        Store<String> store = service.removeEmployeesOfSameJob(jobId);
        return ResponseEntity.status(store.getStatus()).body(store.getInstance());
    }
}
