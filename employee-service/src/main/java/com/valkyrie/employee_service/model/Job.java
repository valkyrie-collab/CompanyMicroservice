package com.valkyrie.employee_service.model;

//import jakarta.persistence.Entity;
//import jakarta.persistence.Id;
//import jakarta.persistence.Table;

//@Entity
//@Table(name = "job")
public class Job {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String description;
    private int salary;
    private String qualification;
    private String skill;

    public String getId() {return id;}

    public String getDescription() {return description;}

    public int getSalary() {return salary;}

    public String getQualification() {return qualification;}

    public String getSkill() {return skill;}

    public Job setId(String id) {
        this.id = id;
        return this;
    }

    public Job setDescription(String description) {
        this.description = description;
        return this;
    }

    public Job setSalary(int salary) {
        this.salary = salary;
        return this;
    }

    public Job setQualification(String qualification) {
        this.qualification = qualification;
        return this;
    }

    public Job setSkill(String skill) {
        this.skill = skill;
        return this;
    }

    @Override
    public String toString() {
        return id + description + salary + qualification + skill;
    }
}
