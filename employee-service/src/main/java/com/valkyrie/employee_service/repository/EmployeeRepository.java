package com.valkyrie.employee_service.repository;

import com.valkyrie.employee_service.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {

    List<Employee> findAllByJobId(String jobId);

    void deleteAllByJobId(String jobId);
}
