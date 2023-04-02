package com.r2comms.r2copy.repository;

import com.r2comms.r2copy.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobRepository extends JpaRepository<Job, Long> {
	List<Job> findByWorkerIdAndStatusAndIsActive(int workId, int i, boolean b);
}
