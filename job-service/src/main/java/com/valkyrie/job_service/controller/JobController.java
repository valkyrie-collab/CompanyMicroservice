package com.valkyrie.job_service.controller;

import com.valkyrie.job_service.model.Job;
import com.valkyrie.job_service.model.Store;
import com.valkyrie.job_service.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/job")
public class JobController {
    private JobService service;
    @Autowired
    private void setService(JobService service) {this.service = service;}

    @PostMapping("/add-job")
    public ResponseEntity<String> save(@RequestParam String token, @RequestBody Job job) {
        Store<String> store = service.save(job);
        return ResponseEntity.status(store.getStatus()).body(store.getInstance());
    }

    @PostMapping("/update-job")
    public ResponseEntity<String> update(@RequestParam String token,
                                         @RequestParam String id, @RequestBody Job job) {
        job = job.setId(id);
        return save(token, job);
    }

    @GetMapping("/find-job-by-id")
    public ResponseEntity<Job> getJobById(@RequestParam String id) {
        Store<Job> store = service.findJobById(id);
        return ResponseEntity.status(store.getStatus()).body(store.getInstance());
    }

    @GetMapping("/find-jobs-by-skill")
    public ResponseEntity<List<Job>> getJobsBySkills(@RequestParam String skill) {
        Store<List<Job>> store = service.findJobBySkills(skill);
        return ResponseEntity.status(store.getStatus()).body(store.getInstance());
    }

    @GetMapping("/find-jobs-by-salary")
    public ResponseEntity<List<Job>> getJobsBySalary(@RequestParam double salary) {
        Store<List<Job>> store = service.findJobBySalary(salary);
        return ResponseEntity.status(store.getStatus()).body(store.getInstance());
    }

    @DeleteMapping("/remove-job-by-id")
    public ResponseEntity<String> removeJobById(@RequestParam String token,
                                                    @RequestParam String id) {
        Store<String> store = service.deleteJobById(id);
        return ResponseEntity.status(store.getStatus()).body(store.getInstance());
    }

    @DeleteMapping("/remove-jobs-by-skills")
    public ResponseEntity<String> removeJobsBySkills(@RequestParam String token,
                                                        @RequestParam String skills) {
        Store<String> store = service.deleteJobBySkills(skills);
        return ResponseEntity.status(store.getStatus()).body(store.getInstance());
    }
}
