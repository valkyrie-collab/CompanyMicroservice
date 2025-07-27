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

    public void setId(String id) {
        this.id = id;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setJobStation(String jobStation) {
        this.jobStation = jobStation;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
}
