package com.valkyrie.employee_service.model;

public class EmployeeWrapper {
    private String id;
    private String firstName;
    private String lastName;
    private String jobStation;
    private String qualification;
    private double salary;
    private Job job;

    public String getId() {return id;}

    public String getFirstName() {return firstName;}

    public String getLastName() {return lastName;}

    public String getQualification() {return qualification;}

    public String getJobStation() {return jobStation;}

    public double getSalary() {return salary;}

    public Job getJob() {return job;}

    public EmployeeWrapper setId(String id) {
        this.id = id;
        return this;
    }

    public EmployeeWrapper setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public EmployeeWrapper setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public EmployeeWrapper setJobStation(String jobStation) {
        this.jobStation = jobStation;
        return this;
    }

    public EmployeeWrapper setQualification(String qualification) {
        this.qualification = qualification;
        return this;
    }

    public EmployeeWrapper setSalary(double salary) {
        this.salary = salary;
        return this;
    }

    public EmployeeWrapper setJob(Job job) {
        this.job = job;
        return this;
    }

    @Override
    public String toString() {
        return id + firstName + lastName + jobStation +
                qualification + salary + job.toString();
    }
}
