package com.valkyrie.employee_service.model;

import jakarta.persistence.*;

@Entity
@Table(name = "employee")
public class Employee {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String firstName;
    private String lastName;
    private String jobStation;
    private String qualification;
    private int salary;

    public String getId() {return id;}

    public String getFirstName() {return firstName;}

    public String getLastName() {return lastName;}

    public String getJobStation() {return jobStation;}

    public String getQualification() {return qualification;}

    public int getSalary() {return salary;}

    public Employee setId(String id) {
        this.id = id;
        return this;
    }

    public Employee setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public Employee setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public Employee setJobStation(String jobStation) {
        this.jobStation = jobStation;
        return this;
    }

    public Employee setQualification(String qualification) {
        this.qualification = qualification;
        return this;
    }

    public Employee setSalary(int salary) {
        this.salary = salary;
        return this;
    }
}
