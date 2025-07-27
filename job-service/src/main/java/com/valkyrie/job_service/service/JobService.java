package com.valkyrie.job_service.service;

import com.valkyrie.job_service.model.Job;
import com.valkyrie.job_service.model.Store;
import com.valkyrie.job_service.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;
import java.util.List;

@Service
public class JobService {
    private static final Job defaultJob = new Job().setId("null").setDescription("null")
            .setQualification("null").setSalary(-1).setSkill("null");
    private JobRepository repo;
    @Autowired
    private void setRepo(JobRepository repo) {this.repo = repo;}

    //For save and update Jobs
    public Store<String> save(Job job) {
        boolean presentId = job.getId() == null;

        if (presentId) {
            job = job.setId("job" + UUID.randomUUID());
            repo.save(job);
            return Store.initialize(HttpStatus.ACCEPTED,
                    "Job with ID = " + job.getId() + " has been saved successfully.....");
        } else if (!repo.findById(job.getId()).orElse(job).toString().equals(job.toString())) {
            repo.save(job);
            return Store.initialize(HttpStatus.ACCEPTED,
                    "Job with ID = " + job.getId() + " has been updated successfully.....");
        }

        return Store.initialize(HttpStatus.BAD_REQUEST, "Job not saved......");
    }

    //Find job By Id
    public Store<Job> findJobById(String id) {
        return Store.initialize(HttpStatus.OK, repo.findById(id).orElse(defaultJob));
    }

    //Find Jobs By Salary
    public Store<List<Job>> findJobBySalary(double salary) {
        List<Job> jobs = repo.findAllBySalary(salary);
        return !jobs.isEmpty()? Store.initialize(HttpStatus.OK, jobs) :
                Store.initialize(HttpStatus.BAD_REQUEST, List.of(defaultJob));
    }

    //Find jobs By Skill
    public Store<List<Job>> findJobBySkills(String skill) {
        List<Job> jobs = repo.findAllBySkill(skill);
        System.out.println(jobs);
        return !jobs.isEmpty()? Store.initialize(HttpStatus.OK, jobs) :
                Store.initialize(HttpStatus.BAD_REQUEST, List.of(defaultJob));
    }

    //Delete Job by id
    public Store<String> deleteJobById(String id) {

        if (repo.findById(id).orElse(null) == null) {
            return Store.initialize(HttpStatus.OK,
                    "There is no Job With ID = " + id + " to deleted.......");
        }

        repo.deleteById(id);
        Job job = repo.findById(id).orElse(null);
        return job == null? Store.initialize(HttpStatus.OK,
                "Job With ID = " + id + " has been deleted successfully.......") :
                Store.initialize(HttpStatus.BAD_REQUEST, "Job not deleted Successfully......");
    }

    //Delete Job by Skills
    @Transactional
    public Store<String> deleteJobBySkills(String skill) {

        if (repo.findAllBySkill(skill).isEmpty()) {
            return Store.initialize(HttpStatus.OK,
                    "There are no Jobs with Skill = " + skill + " to delete.......");
        }

        repo.deleteAllBySkill(skill);
        List<Job> job = repo.findAllBySkill(skill);
        return job.isEmpty()? Store.initialize(HttpStatus.OK,
                "Jobs With Skills = " + skill + " has been deleted successfully.......") :
                Store.initialize(HttpStatus.BAD_REQUEST, "Job not deleted Successfully......");
    }
}
