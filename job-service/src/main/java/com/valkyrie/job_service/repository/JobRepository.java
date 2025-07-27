package com.valkyrie.job_service.repository;

import com.valkyrie.job_service.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job, String> {

    @Query("SELECT j FROM Job j WHERE j.salary >= :salary")
    List<Job> findAllBySalary(@Param("salary") double salary);

    List<Job> findAllBySkill(String skill);

    void deleteAllBySkill(String skills);
}
