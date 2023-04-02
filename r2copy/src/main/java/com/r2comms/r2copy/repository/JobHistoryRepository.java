package com.r2comms.r2copy.repository;

import com.r2comms.r2copy.entity.JobHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobHistoryRepository extends JpaRepository<JobHistory, Long> {
}
