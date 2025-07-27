package com.valkyrie.employee_service.service;

import com.valkyrie.employee_service.feign.JobFeignController;
import com.valkyrie.employee_service.model.Employee;
import com.valkyrie.employee_service.model.EmployeeWrapper;
import com.valkyrie.employee_service.repository.EmployeeRepository;
import com.valkyrie.employee_service.model.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class EmployeeService {
    private static final EmployeeWrapper defaultWrapper = new EmployeeWrapper().setId("null").setFirstName("null")
            .setLastName("null").setQualification("null").setJobStation("null").setSalary(-1);

    private static final Employee defaultEmployee = new Employee().setId("null").setFirstName("null")
            .setLastName("null").setQualification("null").setJobStation("null").setSalary(-1).setJobId("null");

    private EmployeeRepository repo;
    @Autowired
    private void setRepo(EmployeeRepository repo) {this.repo = repo;}

    private JobFeignController feign;
    @Autowired
    private void setFeign(JobFeignController feign) {this.feign = feign;}

    //Employee save and Update
    public Store<String> save(Employee employee) {
        boolean checkId = employee.getId() == null && feign.getJobById(
                employee.getJobId()).getStatusCode().equals(HttpStatusCode.valueOf(200));

        if (checkId) {
            employee = employee.setId("employee" + UUID.randomUUID());
            repo.save(employee);
            return Store.initialize(HttpStatus.ACCEPTED,
                    "Employee with ID = " + employee.getId() + " saved successfully......");
        } else if (!repo.findById(employee.getId()).orElse(employee).toString().equals(employee.toString())) {
            repo.save(employee);
            return Store.initialize(HttpStatus.ACCEPTED,
                    "Employee with ID = " + employee.getId() + " has been updated......");
        }

        return Store.initialize(HttpStatus.BAD_REQUEST, "Employee not saved.....");
    }

    //Find Employee By Id
    public Store<EmployeeWrapper> findEmployeeById(String id) {
        Employee employee = repo.findById(id).orElse(defaultEmployee);

        if (!employee.toString().equals(defaultEmployee.toString())) {
            EmployeeWrapper wrapper = new EmployeeWrapper().setId(employee.getId())
                    .setJob(feign.getJobById(employee.getJobId()).getBody())
                    .setFirstName(employee.getFirstName()).setLastName(employee.getLastName())
                    .setQualification(employee.getQualification())
                    .setJobStation(employee.getJobStation()).setSalary(employee.getSalary());
            return Store.initialize(HttpStatus.OK, wrapper);
        }

        return Store.initialize(HttpStatus.OK, defaultWrapper);
    }

    //Find Employee By JobId
    public Store<List<EmployeeWrapper>> findEmployeeWithSameJob(String jobId) {
        List<Employee> employees = repo.findAllByJobId(jobId);
        List<EmployeeWrapper> wrappers = new ArrayList<>();

        if (!employees.isEmpty()) {

            for (Employee employee : employees) {
                wrappers.add(new EmployeeWrapper().setId(employee.getId())
                        .setJob(feign.getJobById(employee.getJobId()).getBody())
                        .setFirstName(employee.getFirstName()).setLastName(employee.getLastName())
                        .setQualification(employee.getQualification())
                        .setJobStation(employee.getJobStation()).setSalary(employee.getSalary())
                );
            }

            return Store.initialize(HttpStatus.OK, wrappers);
        }

        return Store.initialize(HttpStatus.BAD_REQUEST, List.of(defaultWrapper));
    }

    //Remove Employee By Id
    public Store<String> removeEmployeeById(String id) {

        if (repo.findById(id).orElse(null) == null) {
            return Store.initialize(HttpStatus.OK,
                    "The Employee with ID = " + id + " has already been removed.....");
        }

        repo.deleteById(id);
        Employee employee = repo.findById(id).orElse(null);

        return employee == null? Store.initialize(HttpStatus.OK, "Deletion is successful...") :
                Store.initialize(HttpStatus.BAD_REQUEST,
                        "The Employee with ID = " + id + " is not deleted....");
    }

    //Remove Employee By JobId
    @Transactional
    public Store<String> removeEmployeesOfSameJob(String jobId) {

        if (repo.findAllByJobId(jobId).isEmpty()) {
            return Store.initialize(HttpStatus.OK,
                    "The Employees with JobId = " + jobId + " are already been removed....");
        }

        repo.deleteAllByJobId(jobId);
        List<Employee> employees = repo.findAllByJobId(jobId);

        return employees.isEmpty()? Store.initialize(HttpStatus.OK, "Deletion is successful...") :
                Store.initialize(HttpStatus.BAD_REQUEST,
                        "The Employees with JobId = " + jobId + " is not deleted....");
    }
}
