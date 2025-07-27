package com.valkyrie.employee_service.feign;

import com.valkyrie.employee_service.model.Job;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("JOB-SERVICE")
public interface JobFeignController {

    @GetMapping("/job/find-job-by-id")
    ResponseEntity<Job> getJobById(@RequestParam String id);

}
